package com.etollpay.srpc.service.tool;

import com.alibaba.fastjson.JSON;
import com.etollpay.srpc.service.component.IMetadataRecorder;
import com.etollpay.srpc.service.component.SpringContextHolder;
import com.etollpay.srpc.service.processor.ProcessorExecutor;
import com.etollpay.srpc.service.servlet.IAsyncResponseInvoker;
import com.etollpay.srpc.service.servlet.IResponseBuilder;
import com.etollpay.srpc.service.util.*;
import com.etollpay.srpc.standard.basic.IntfError;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.spi.ServiceHelper;
import com.etollpay.srpc.tool.spi.intf.IEncryptor;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import org.bouncycastle.util.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class BizRunner implements Runnable {
    private static Logger log = LoggerFactory.getLogger(BizRunner.class);

    private IMetadataReader metadataReader;
    private IBizFileReader bizFileReader;
    private IResponseBuilder responseBuilder;
    private IAsyncResponseInvoker asyncResponseInvoker;
    private IMetadataRecorder metadataRecorder;
    private AsyncContext asyncContext;

    private boolean async = false;

    public BizRunner(AsyncContext asyncContext, IMetadataReader metadataReader, IBizFileReader bizFileReader,
                     IResponseBuilder responseBuilder, IAsyncResponseInvoker asyncResponseInvoker) {
        this.asyncContext = asyncContext;
        this.metadataReader = metadataReader;
        this.bizFileReader = bizFileReader;
        this.responseBuilder = responseBuilder;
        this.asyncResponseInvoker = asyncResponseInvoker;
        this.metadataRecorder = SpringContextHolder.getBean(IMetadataRecorder.class);
    }

    /**
     * 解包业务数据文件
     * @param workspace         工作目录，即解包位置
     * @param metadata          元数据
     * @param hashAlgorithm     哈希算法，非null时将计算解包前元数据的哈希值
     * @return                  解包前元数据的哈希值
     */
    private String unpack(Path workspace, Metadata metadata, String hashAlgorithm) {
        if (metadata.isHasFile()) {
            String fileName = MetadataHelper.getBizFileName(metadata);
            Path archivePath = Paths.get(FilePath.getInboundPath(metadata), fileName);
            Assert.is(archivePath == null || !Files.exists(archivePath), IntfError.FILE_NOT_FOUND, fileName);
            IEncryptor encryptor = ServiceHelper.getProvider(
                    metadata.getFileEncryption() != null ? metadata.getFileEncryption() : MetadataHelper.getDefaultEncryption(),
                    IEncryptor.class);
            Assert.is(encryptor == null, IntfError.BIZPROCESSOR_NOT_FOUND, metadata.getFileEncryption());
            try {
                InputStream inputStream = Files.newInputStream(archivePath);
                if (hashAlgorithm != null) {
                    inputStream = new InputStreamWithHash(inputStream, hashAlgorithm);
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(
                        encryptor.getDecryptStream(inputStream, metadata.getRecipient(),
                                metadata.getSender()),8 * 1024);
                Path bizFilePath = workspace.resolve(MetadataHelper.getOriginBizFileName(metadata));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                        Files.newOutputStream(bizFilePath));
                try {
                    Streams.pipeAll(bufferedInputStream, bufferedOutputStream);
                    if (inputStream instanceof InputStreamWithHash) {
                        return ((InputStreamWithHash) inputStream).getHashCode();
                    }
                } finally {
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                }
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            } catch (NoSuchAlgorithmException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            }
        }
        return null;
    }




    public Metadata execute(Metadata metadata, HttpServletRequest request) {
        log.debug("metadata: {}", JSON.toJSONString(metadata));
        ProcessorExecutor.executePreProcessor(metadata, request);

        // record request
        if (metadataRecorder != null) {
            metadataRecorder.save(metadata);
        }

        // read biz file
        String hashCode = bizFileReader.read(metadata, request);

        // check hash
        if (hashCode != null) {
            Assert.is(!hashCode.equalsIgnoreCase(metadata.getFileHashCode()), IntfError.ILLEGAL_FILE_HASH,
                    metadata.getFileHashCode(), hashCode);
        }

        Path workspace = Paths.get(FilePath.getTempPath(true));
        hashCode = unpack(workspace, metadata, hashCode == null ? MetadataHelper.getDefaultHashAlgorithm() : null);
        if (hashCode != null) {
            Assert.is(!hashCode.equalsIgnoreCase(metadata.getFileHashCode()), IntfError.ILLEGAL_FILE_HASH,
                    metadata.getFileHashCode(), hashCode);
        }

        Path bizFilePath = workspace.resolve(MetadataHelper.getOriginBizFileName(metadata));
        if (Files.exists(bizFilePath)) {
            try {
                long fileSize = Files.size(bizFilePath);
                long limited = SysConfig.getLong("asyncLimited." + metadata.getApiName(), Long.MAX_VALUE);
                if (limited == Long.MAX_VALUE) {
                    limited = SysConfig.getLong("asyncLimited", Long.MAX_VALUE);
                }
                async = fileSize > limited;
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
                throw new ServiceException(e, IntfError.INTERNAL_ERROR, e.getLocalizedMessage());
            }
        }
        if (async) {
            return ProcessorExecutor.executeBizProcessor(workspace, metadata, null, asyncResponseInvoker);
        } else {
            return ProcessorExecutor.executeBizProcessor(workspace, metadata, null);
        }
    }

    @Override
    public void run() {
        HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
        // get metadata
        Metadata metadata = metadataReader.read(request);
        try {
            Metadata resMetadata = execute(metadata, request);
            if (async) {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else {
                if (metadataRecorder != null && SysConfig.getBool("srpc.record.resp", true)) {
                    metadataRecorder.save(resMetadata);
                }
                responseBuilder.success(resMetadata, response);
            }
        } catch (Throwable t) {
            log.error(t.getLocalizedMessage(), t);
            responseBuilder.error(t, metadata, response);
        }
        asyncContext.complete();
    }
}
