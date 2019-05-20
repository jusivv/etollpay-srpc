package com.etollpay.srpc.invoker;

public class TargetInfo {
    /**
     * 目标URL
     */
    private String targetUrl;
    /**
     * 接口名
     */
    private String apiName;
    /**
     * 接受者
     */
    private String recipient;
    /**
     * 接受者的secretKey
     */
    private byte[] secretKey;
    /**
     * 源请求ID
     */
    private String originReqId;
    /**
     * 错误代码
     */
    private int errorCode;

    public TargetInfo(String targetUrl, String apiName, String recipient, byte[] secretKey, String originReqId, int errorCode) {
        this.targetUrl = targetUrl;
        this.apiName = apiName;
        this.recipient = recipient;
        this.secretKey = secretKey;
        this.originReqId = originReqId;
        this.errorCode = errorCode;
    }

    public TargetInfo(String targetUrl, String apiName, String recipient, byte[] secretKey) {
        this.targetUrl = targetUrl;
        this.apiName = apiName;
        this.recipient = recipient;
        this.secretKey = secretKey;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getApiName() {
        return apiName;
    }

    public String getRecipient() {
        return recipient;
    }

    public byte[] getSecretKey() {
        return secretKey;
    }

    public String getOriginReqId() {
        return originReqId;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
