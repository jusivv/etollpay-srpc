package com.etollpay.srpc.test;

/**
 * 接口测试程序配置项
 */
public class ConfigParameter {
    /**
     * 指令模式
     */
    private CommandMode mode;
    /**
     * 接口URL
     */
    private String targetUrl;
    /**
     * 发送者
     */
    private String sender;
    /**
     * 接口名称
     */
    private String apiName;
    /**
     * Base64形式的secretKey
     */
    private String secretKeyBase64;
    /**
     * 接收者
     */
    private String recipient;
    /**
     * 加密方式
     */
    private String encryptionTag;

    public CommandMode getMode() {
        return mode;
    }

    public void setMode(CommandMode mode) {
        this.mode = mode;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getSecretKeyBase64() {
        return secretKeyBase64;
    }

    public void setSecretKeyBase64(String secretKeyBase64) {
        this.secretKeyBase64 = secretKeyBase64;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getEncryptionTag() {
        return encryptionTag;
    }

    public void setEncryptionTag(String encryptionTag) {
        this.encryptionTag = encryptionTag;
    }
}
