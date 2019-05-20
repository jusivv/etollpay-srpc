package com.etollpay.srpc.test;

import com.alibaba.fastjson.JSON;
import com.etollpay.srpc.invoker.EtcHttpClient;
import com.etollpay.srpc.invoker.ResponseMessage;
import com.etollpay.srpc.invoker.TargetInfo;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.OutputStreamWithHash;
import com.etollpay.srpc.tool.spi.ServiceHelper;
import com.etollpay.srpc.tool.spi.intf.IEncryptor;
import com.etollpay.srpc.tool.standard.BizDataBuilder;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Command {

    private static Logger log = LoggerFactory.getLogger(Command.class);

    private static String dir = System.getProperty("user.dir");

    /**
     *
     * @param args mode configFile bizFiles
     * @throws IOException
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchFieldException
     */
    public static void main(String[] args) throws IOException, IllegalAccessException, NoSuchAlgorithmException, NoSuchFieldException {
        if (args == null || args.length < 1) {
            throw new RuntimeException("Illegal parameter");
        }
        ConfigParameter param = JSON.parseObject(Command.class.getClassLoader().getResourceAsStream(args[0]),
                ConfigParameter.class);

        log.debug("parameters: {}", JSON.toJSONString(param));

        byte[] secretKey = Common.decodeBase64(param.getSecretKeyBase64());

        String encryptionTag = Common.isBlank(param.getEncryptionTag()) ? MetadataHelper.getDefaultEncryption() :
                param.getEncryptionTag() ;
        IEncryptor encryptor = ServiceHelper.getProvider(encryptionTag, IEncryptor.class);

        switch (param.getMode()) {
            case CREATE_BIZ_FILE: {
                List<String> fileNames = new ArrayList<String>();
                if (args.length > 1) {
                    for (int i = 1; i < args.length; i++) {
                        fileNames.add(args[i]);
                    }
                }
                createBizDataFile(param.getApiName(), param.getSender(), param.getRecipient(), secretKey, encryptor,
                        fileNames.toArray(new String[0]));
                break;
            }
            case NOTIFY_REQUEST: {
                if (args.length < 2) {
                    throw new RuntimeException("Illegal parameter");
                }
                EtcHttpClient etcHttpClient = new EtcHttpClient(param.getSender(), encryptor);
                Metadata metadata = JSON.parseObject(Files.newInputStream(Paths.get(dir, args[1])),
                        Charset.forName("UTF-8"), Metadata.class);
                log.debug("metadata: {}", JSON.toJSONString(metadata));
                ResponseMessage responseMessage = etcHttpClient.syncNotify(param.getTargetUrl(), secretKey, metadata);
                //
                writeResponse(responseMessage);
                break;
            }
            case MESSAGE_REQUEST: {
                if (args.length > 1) {
                    EtcHttpClient etcHttpClient = new EtcHttpClient(param.getSender(), encryptor);
                    TargetInfo targetInfo = new TargetInfo(param.getTargetUrl(), param.getApiName(), param.getRecipient(),
                            secretKey);
                    //
                    BizDataBuilder bizDataBuilder = new BizDataBuilder();
                    for (int i = 1; i < args.length; i++) {
                        bizDataBuilder.addFile(Paths.get(dir, args[i]));
                    }
                    ResponseMessage responseMessage = etcHttpClient.syncInvoke(targetInfo, bizDataBuilder);
                    writeResponse(responseMessage);
                } else {
                    log.error("biz file not found.");
                }
                break;
            }
        }

    }

    private static void writeResponse(ResponseMessage responseMessage) throws IOException {
        if (responseMessage.getStatusCode() == 200) {
            if (responseMessage.getPlainData() != null) {
                OutputStream outputStream = Files.newOutputStream(Paths.get(dir,
                        MetadataHelper.getBizFileName(responseMessage.getResMetadata()) + ".txt"));
                try {
                    outputStream.write(responseMessage.getPlainData());
                } finally {
                    outputStream.flush();
                    outputStream.close();
                }
            }
        } else {
            log.error("status code: {}, plain text: {}", responseMessage.getStatusCode(),
                    new String(responseMessage.getPlainData(), Charset.forName("UTF-8")));
        }
    }

    private static void createBizDataFile(String apiName, String sender, String recipient, byte[] secretKey, IEncryptor encryptor,
                                          String[] bizFiles)
            throws IOException, NoSuchAlgorithmException, NoSuchFieldException, IllegalAccessException {
        Metadata metadata = MetadataHelper.createMetadata(apiName, sender, recipient);
        if (bizFiles != null && bizFiles.length > 0) {
            metadata.setHasFile(true);
            metadata.setFileEncryption(encryptor.getEncryption());
            BizDataBuilder bizDataBuilder = new BizDataBuilder();
            for (int i = 0; i < bizFiles.length; i++) {
                log.debug("add file: {}", bizFiles[i]);
                bizDataBuilder.addFile(Paths.get(dir, bizFiles[i]));
            }
            metadata.setFileFormat(bizDataBuilder.getOutputFileType());
            String fileName = MetadataHelper.getBizFileName(metadata);
            OutputStreamWithHash output = new OutputStreamWithHash(Files.newOutputStream(Paths.get(dir, fileName)),
                    "MD5");
            OutputStream encStream = encryptor.getEncryptStream(output, metadata.getRecipient(), sender);
            bizDataBuilder.build(encStream);
            encStream.flush();
            encStream.close();
            metadata.setFileHashCode(output.getHashCode());
        }
        metadata.setMac(MetadataHelper.getMac(metadata, secretKey));
        JSON.writeJSONString(
                Files.newOutputStream(Paths.get(dir, "metadata." + metadata.getRequestId() + ".json")),
                Charset.forName("UTF-8"), metadata);
    }

}
