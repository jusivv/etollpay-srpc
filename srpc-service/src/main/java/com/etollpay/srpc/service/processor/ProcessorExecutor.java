package com.etollpay.srpc.service.processor;

import com.etollpay.srpc.service.component.SpringContextHolder;
import com.etollpay.srpc.service.tool.BizRunnerExecutor;
import com.etollpay.srpc.service.servlet.IAsyncResponseInvoker;
import com.etollpay.srpc.service.util.Assert;
import com.etollpay.srpc.service.util.FilePath;
import com.etollpay.srpc.service.util.ServiceException;
import com.etollpay.srpc.service.util.SysConfig;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.OutputStreamWithHash;
import com.etollpay.srpc.tool.spi.ServiceHelper;
import com.etollpay.srpc.tool.spi.intf.IEncryptor;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import org.bouncycastle.util.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.ServiceConfigurationError;

public class ProcessorExecutor {
    private static Logger log = LoggerFactory.getLogger(ProcessorExecutor.class);

    /**
     * 执行业务处理器
     * SPI实例化对象失败是抛出Error（ServiceConfigurationError）而不是Exception
     * @param workspace             工作空间
     * @param metadata              元数据
     */
    public static Metadata executeBizProcessor(Path workspace, Metadata metadata, IEncryptor encryptor) {
        long beginTime = System.currentTimeMillis();
        log.debug("begin process {} from {} to {}, request id: {}", metadata.getApiName(), metadata.getSender(),
                metadata.getRecipient(), metadata.getRequestId());
        try {
            // find biz processor
            IBizProcessor bizProcessor = ServiceHelper.getProvider(metadata.getApiName(), IBizProcessor.class);
            Assert.is(bizProcessor == null, IntfError.BIZPROCESSOR_NOT_FOUND, metadata.getApiName());

            // inject metadata
            Class<?> clazz = bizProcessor.getClass();
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null) {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    if (field.getType().getName().equals(Metadata.class.getName())) {
                        field.setAccessible(true);
                        try {
                            field.set(bizProcessor, metadata);
                        } catch (IllegalAccessException e) {
                            log.error(e.getLocalizedMessage(), e);
                            throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
                        }
                        break;
                    }
                }
            }

            // execute processor
            Path bizDataPath = workspace.resolve(MetadataHelper.getOriginBizFileName(metadata));
            String resPath = null;
            ByteArrayOutputStream resOutput = new ByteArrayOutputStream();
            // execute processor in another transaction
            TransactionStatus transactionStatus = null;
            JpaTransactionManager transactionManager = SpringContextHolder.getBean(JpaTransactionManager.class);
            if (transactionManager != null) {
                DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
                defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);
            } else {
                log.warn("fail to begin transaction !");
            }
            try {
                if (metadata.getReqType().equalsIgnoreCase(Metadata.REQUEST_TYPE_REQ)) {
                    resPath = bizProcessor.processRequest(workspace, bizDataPath, metadata.getSender(),
                            resOutput);
                } else if (metadata.getReqType().equalsIgnoreCase(Metadata.REQUEST_TYPE_RES)) {
                    resPath = bizProcessor.processResponse(workspace, bizDataPath, metadata.getSender(),
                            resOutput);
                } else if (metadata.getReqType().equalsIgnoreCase(Metadata.REQUEST_TYPE_ERR)) {
                    resPath = bizProcessor.processError(workspace, bizDataPath, metadata.getSender(),
                            resOutput);
                } else {
                    throw new ServiceException(IntfError.ILLEGAL_REQTYP);
                }
                if (transactionManager != null) {
                    transactionManager.commit(transactionStatus);
                }
                if (bizProcessor instanceof AbstractBizProcessor) {
                    ((AbstractBizProcessor) bizProcessor).doPublish();
                }
            } catch (Throwable t) {
                log.error(t.getLocalizedMessage(), t);
                // rollback
                if (transactionStatus != null && transactionManager != null) {
                    if (!transactionStatus.isCompleted()) {
                        transactionManager.rollback(transactionStatus);
                    }
                }
                // throw
                throw t instanceof ServiceException ? (ServiceException)t : new ServiceException(
                        t, IntfError.INTERNAL_ERROR, t.getLocalizedMessage());
            }
            Metadata resMetadata = metadata.response();
            if (resMetadata.getFileEncryption() == null) {
                resMetadata.setFileEncryption(MetadataHelper.getDefaultEncryption());
            }
            resMetadata.setHasFile(resOutput.size() > 0 || resPath != null);
            // encrypt
            if (resMetadata.isHasFile()) {
                resMetadata.setFileFormat(
                        resOutput.size() > 0 ? resPath.toUpperCase() :
                                resPath.substring(resPath.lastIndexOf(".") + 1).toUpperCase()
                );
                if (encryptor == null) {
                    encryptor = ServiceHelper.getProvider(resMetadata.getFileEncryption(), IEncryptor.class);
                    Assert.is(encryptor == null, IntfError.UNSUPPORT_ENCRYPTION, resMetadata.getApiName());
                }
                try {
                    InputStream resInput = resOutput.size() > 0 ?
                            new ByteArrayInputStream(resOutput.toByteArray()) :
                            new BufferedInputStream(Files.newInputStream(Paths.get(resPath)), 8 * 1024);
                    Path resArchivePath = Paths.get(FilePath.getOutboundPath(resMetadata, true),
                            MetadataHelper.getBizFileName(resMetadata));

                    OutputStreamWithHash outputStreamWithHash = new OutputStreamWithHash(
                            new BufferedOutputStream(Files.newOutputStream(resArchivePath), 8 * 1024),
                            "MD5");
                    OutputStream outputStream = encryptor.getEncryptStream(outputStreamWithHash,
                            resMetadata.getRecipient(), resMetadata.getSender());
                    try {
                        Streams.pipeAll(resInput, outputStream);
                    } finally {
                        outputStream.close();
                        resInput.close();
                    }
                    resMetadata.setFileHashCode(outputStreamWithHash.getHashCode());
                } catch (IOException e) {
                    log.error(e.getLocalizedMessage(), e);
                    throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
                } catch (NoSuchAlgorithmException e) {
                    log.error(e.getLocalizedMessage(), e);
                    throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
                }
            }
            resMetadata.setMac(MetadataHelper.getMac(resMetadata,
                    Common.decodeBase64(SysConfig.getString(
                            "secretKey." + resMetadata.getRecipient()))));
            return resMetadata;
        } finally {
            try {
                FilePath.deleteDirectory(workspace);
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
            }
            log.debug("end process {} from {} to {} in {} ms, request id: {}", metadata.getApiName(), metadata.getSender(),
                    metadata.getRecipient(), System.currentTimeMillis() - beginTime, metadata.getRequestId());
        }
    }

    /**
     *
     * @param workspace
     * @param metadata
     * @param asyncResponseInvoker
     * @return
     */
    public static Metadata executeBizProcessor(final Path workspace, final Metadata metadata, final IEncryptor encryptor,
                                               final IAsyncResponseInvoker asyncResponseInvoker) {
        log.debug("start async process {} from {} to {}, request id: {}.", metadata.getApiName(), metadata.getSender(),
                metadata.getRecipient(), metadata.getRequestId());
        BizRunnerExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Metadata resMetadata = executeBizProcessor(workspace, metadata, encryptor);
                    asyncResponseInvoker.success(resMetadata);
                } catch (Throwable t) {
                    log.error(t.getLocalizedMessage(), t);
                    asyncResponseInvoker.error(t, metadata);
                }
            }
        }, metadata);
        return metadata.response();
    }

    /**
     * 执行前置处理器
     * SPI实例化对象失败是抛出Error（ServiceConfigurationError）而不是Exception
     * @param metadata
     * @param request
     * @throws ServiceException
     */
    public static void executePreProcessor(Metadata metadata, HttpServletRequest request) {
        try {
            for (Iterator<IPreProcessor> it = ServiceHelper.getProviders(IPreProcessor.class); it.hasNext(); ) {
                IPreProcessor preProcessor = it.next();
                if (preProcessor.accept(metadata.getReqType())) {
                    preProcessor.process(metadata, request);
                }
            }
        } catch (ServiceConfigurationError e) {
            throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
        }
    }
}
