package com.etollpay.srpc.invoker;

import com.alibaba.fastjson.JSON;
import com.etollpay.srpc.standard.basic.Metadata;
import com.etollpay.srpc.tool.Common;
import com.etollpay.srpc.tool.OutputStreamWithHash;
import com.etollpay.srpc.tool.ValuePicker;
import com.etollpay.srpc.tool.spi.ServiceHelper;
import com.etollpay.srpc.tool.spi.intf.IEncryptor;
import com.etollpay.srpc.tool.standard.BizDataBuilder;
import com.etollpay.srpc.tool.standard.MetadataHelper;
import okhttp3.*;
import org.bouncycastle.util.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EtcHttpClient {

    private static Logger log = LoggerFactory.getLogger(EtcHttpClient.class);

    private OkHttpClient okHttpClient;
    private String myPartnerId;
    private IEncryptor encryptor;

    /**
     * 构造方法
     * @param myPartnerId   己方编号
     * @param encryptor     加解密器
     */
    public EtcHttpClient(String myPartnerId, IEncryptor encryptor) {
        this.myPartnerId = myPartnerId;
        this.encryptor = encryptor != null ? encryptor : ServiceHelper.getProvider(
                MetadataHelper.getDefaultEncryption(), IEncryptor.class);
        okHttpClient = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).build();
    }

    /**
     * 构造方法，使用默认的加解密器（GnuPG）
     * @param myPartnerId   己方编号
     */
    public EtcHttpClient(String myPartnerId) {
        this(myPartnerId, null);
    }

    /**
     * 设置加解密器
     * @param encryptor
     */
    public void setEncryptor(IEncryptor encryptor) {
        this.encryptor = encryptor != null ? encryptor : ServiceHelper.getProvider(
                MetadataHelper.getDefaultEncryption(), IEncryptor.class);
    }

    /**
     * 接口调用方法
     * @param targetInfo        接口目标信息
     * @param bizDataBuilder    业务数据构造器
     * @param etcCallback       异步回调（为null时为同步执行）
     * @return                  响应对象（异步时返回null）
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws IOException
     */
    public ResponseMessage invoke(TargetInfo targetInfo, BizDataBuilder bizDataBuilder, final EtcCallback etcCallback)
            throws NoSuchAlgorithmException, IllegalAccessException, NoSuchFieldException, IOException {
        // create metadata
        Metadata metadata = MetadataHelper.createMetadata(targetInfo.getApiName(), myPartnerId,
                targetInfo.getRecipient(), targetInfo.getOriginReqId(), targetInfo.getErrorCode());
        metadata.setHasFile(bizDataBuilder != null && bizDataBuilder.getOutputFileType() != null);

        // get biz data
        ByteArrayOutputStream sendData = null;
        if (metadata.isHasFile()) {
            metadata.setFileFormat(bizDataBuilder.getOutputFileType());
            metadata.setFileEncryption(encryptor.getEncryption());
            sendData = new ByteArrayOutputStream();
            OutputStreamWithHash outputStreamWithHash = new OutputStreamWithHash(sendData, "MD5");
            OutputStream output = encryptor.getEncryptStream(outputStreamWithHash, targetInfo.getRecipient(),
                    myPartnerId);
            bizDataBuilder.build(output);
            output.close();
            metadata.setFileHashCode(outputStreamWithHash.getHashCode());
        }
        metadata.setMac(MetadataHelper.getMac(metadata, targetInfo.getSecretKey()));

        log.debug("Metadata: {}", JSON.toJSONString(metadata));

        // set header
        Map<String, String> headerMap = MetadataHelper.getHeader(metadata);
        Request.Builder requestBuilder = new Request.Builder();
        for (Iterator<String> it = headerMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            requestBuilder.addHeader(key, headerMap.get(key));
        }

        RequestBody body = null;
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        if (metadata.isHasFile()) {
            RequestBody requestBody = RequestBody.create(MediaType.get("application/octet-stream"),
                    sendData.toByteArray());
            bodyBuilder.addFormDataPart("file", MetadataHelper.getBizFileName(metadata), requestBody);
            body = bodyBuilder.build();
        } else {
            body = RequestBody.create(null, new byte[0]);
        }

        Request request = requestBuilder.url(targetInfo.getTargetUrl()).post(body).tag(this).build();
        if (etcCallback != null) {
            log.debug("async invoke message api {} on target [{}] body length {} bytes", targetInfo.getApiName(),
                    targetInfo.getTargetUrl(), body != null ? body.contentLength() : 0);
            okHttpClient.newCall(request).enqueue(createOkHttpCallback(etcCallback, targetInfo.getSecretKey()));
            return null;
        } else {
            log.debug("sync invoke message api {} on target [{}] body length {} bytes", targetInfo.getApiName(),
                    targetInfo.getTargetUrl(), body != null ? body.contentLength() : 0);
            Response response = okHttpClient.newCall(request).execute();
            return parseMessageResponse(response, targetInfo.getSecretKey());
        }

    }

    /**
     * 同步接口调用方法
     * @param targetInfo        接口目标信息
     * @param bizDataBuilder    业务数据构造器
     * @return                  响应对象
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     */
    public ResponseMessage syncInvoke(TargetInfo targetInfo, BizDataBuilder bizDataBuilder) throws IOException,
            NoSuchFieldException,
            NoSuchAlgorithmException, IllegalAccessException {
        return invoke(targetInfo, bizDataBuilder, null);
    }

    /**
     * 异步接口调用方法
     * @param targetInfo        接口地址
     * @param bizDataBuilder    业务数据构造器
     * @param etcCallback       回调对象
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     */
    public void asyncInvoke(TargetInfo targetInfo, BizDataBuilder bizDataBuilder, final EtcCallback etcCallback)
            throws IOException, NoSuchFieldException, NoSuchAlgorithmException, IllegalAccessException {
        if (etcCallback == null) {
            throw new RuntimeException("etcCallback must be not null!");
        }
        invoke(targetInfo, bizDataBuilder, etcCallback);
    }

    /**
     * 调用通知接口
     * @param url               接口地址
     * @param secretKey         接收方的secretKey
     * @param metadata          元数据
     * @param etcCallback       回调方法
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     */
    public ResponseMessage notify(String url, byte[] secretKey, Metadata metadata, final EtcCallback etcCallback)
            throws IOException, NoSuchAlgorithmException, IllegalAccessException {

        metadata.setSender(myPartnerId);
        metadata.setMac(MetadataHelper.getMac(metadata, secretKey));

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                JSON.toJSONString(metadata));

        Request.Builder requestBuilder = new Request.Builder();

        Request request = requestBuilder.url(url).post(requestBody).tag(this).build();
        if (etcCallback != null) {
            log.debug("async invoke notify api {} on target [{}]", metadata.getApiName(), url);
            okHttpClient.newCall(request).enqueue(createOkHttpCallback(etcCallback, secretKey));
            return null;
        } else {
            log.debug("sync invoke notify api {} on target [{}]", metadata.getApiName(), url);
            Response response = okHttpClient.newCall(request).execute();
            return parseNotifyResponse(response, secretKey);
        }
    }

    /**
     * 通知接口同步调用
     * @param url       接口地址
     * @param secretKey 接收方的secretKey
     * @param metadata  元数据
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public ResponseMessage syncNotify(String url, byte[] secretKey, Metadata metadata) throws IllegalAccessException,
            NoSuchAlgorithmException, IOException {
        return notify(url, secretKey, metadata, null);
    }

    /**
     * 通知接口异步调用
     * @param url           接口地址
     * @param metadata      元数据
     * @param etcCallback   回调方法
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     */
    public void asyncNotify(String url, byte[] secretKey, Metadata metadata, final EtcCallback etcCallback)
            throws IOException, NoSuchAlgorithmException, IllegalAccessException {
        if (etcCallback == null) {
            throw new RuntimeException("etcCallback must be not null!");
        }
        notify(url, secretKey, metadata, etcCallback);
    }

    private ResponseMessage parseNotifyResponse(Response response, byte[] secretKey) throws IOException,
            IllegalAccessException {
        byte[] responseBody = response.body().bytes();
        Metadata resMetadata = JSON.parseObject(responseBody, 0, responseBody.length, Charset.forName("UTF-8"),
                Metadata.class);
        if (response.code() == 200) {
            // check mac
            if (!MetadataHelper.verifyMac(resMetadata, resMetadata.getMac(), secretKey)) {
                throw new IllegalAccessException("illegal mac.");
            }
        }
        return new ResponseMessage(response.code(), resMetadata, null, responseBody);
    }

    /**
     * 纯HTTP协议响应信息解析方法
     * @param response  响应对象
     * @param secretKey 对端的secretKey
     * @return
     * @throws IllegalAccessException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private ResponseMessage parseMessageResponse(Response response, byte[] secretKey) throws IllegalAccessException,
            IOException, NoSuchAlgorithmException {
        // create metadata
        final Headers headers = response.headers();
        Metadata resMetadata = MetadataHelper.fromHeader(new ValuePicker<String>() {
            public String pick(String key) {
                return headers.get(key);
            }
        });

        if (response.code() != 200) {
            return new ResponseMessage(response.code(), resMetadata, null, response.body().bytes());
        }
        // check mac
        if (!MetadataHelper.verifyMac(resMetadata, resMetadata.getMac(), secretKey)) {
            throw new IllegalAccessException("illegal mac.");
        }

        ByteArrayOutputStream cipherData = new ByteArrayOutputStream();
        ByteArrayOutputStream plainData = new ByteArrayOutputStream();
        if (resMetadata.isHasFile()) {
            // fetch file
            InputStream inputStream = response.body().byteStream();
            // cipher data
            String hashCode = Common.copyStreamWithHashCode(inputStream, "MD5", cipherData);
            // check hash code
            if (!hashCode.equalsIgnoreCase(resMetadata.getFileHashCode())) {
                throw new IllegalAccessException("mismatch file hash code");
            }
            // decrypt
            IEncryptor resEncryptor = null;
            if (resMetadata.getFileEncryption().equalsIgnoreCase(encryptor.getEncryption())) {
                resEncryptor = encryptor;
            } else {
                resEncryptor = ServiceHelper.getProvider(
                        resMetadata.getFileEncryption(), IEncryptor.class);
                if (resEncryptor == null) {
                    throw new RuntimeException("no encryptor with "
                            + resMetadata.getFileEncryption());
                }
            }
            InputStream decryptStream = resEncryptor.getDecryptStream(
                    new ByteArrayInputStream(cipherData.toByteArray()),
                    resMetadata.getRecipient(), resMetadata.getSender());
            Streams.pipeAll(decryptStream, plainData);
        }
        return new ResponseMessage(response.code(), resMetadata,
                cipherData.size() > 0 ? cipherData.toByteArray() : null,
                plainData.size() > 0 ? plainData.toByteArray() : null);

    }

    /**
     * 构造OkHttp回调方法
     * @param etcCallback
     * @return
     */
    public Callback createOkHttpCallback(final EtcCallback etcCallback, final byte[] secretKey) {
        return new Callback() {
            public void onFailure(Call call, IOException e) {
                etcCallback.requestError(call, e);
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    etcCallback.response(parseMessageResponse(response, secretKey));
                } catch (Throwable t) {
                    etcCallback.onError(t);
                }
            }
        };
    }

}
