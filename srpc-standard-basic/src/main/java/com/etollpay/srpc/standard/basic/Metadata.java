package com.etollpay.srpc.standard.basic;

import java.util.UUID;

public class Metadata {

    /**
     * 请求类别：业务请求
     */
    public static final String REQUEST_TYPE_REQ = "REQ";
    /**
     * 请求类别：请求应答
     */
    public static final String REQUEST_TYPE_RES = "RES";
    /**
     * 请求类别：异常通知
     */
    public static final String REQUEST_TYPE_ERR = "ERR";

    /**
     * 业务数据文件格式：压缩文件
     */
    public static final String FILE_FORMAT_ZIP = "ZIP";
    /**
     * 业务数据文件格式：JSON文件
     */
    public static final String FILE_FORMAT_JSON = "JSON";
    /**
     * 业务数据文件格式：CSV文件
     */
    public static final String FILE_FORMAT_CSV = "CSV";

    /**
     * 业务数据文件加密方式：PGP（GnuPG）
     */
    public static final String FILE_ENCRYPTION_PGP = "PGP";
    /**
     * 业务数据文件加密方式：RAW（不加密）
     */
    public static final String FILE_ENCRYPTION_RAW = "RAW";

    /**
     * 请求ID，请求的唯一标识，建议使用UUID
     */
    private String requestId;
    /**
     * 请求类别
     */
    private String reqType;
    /**
     * API名称，要调用的API名称
     */
    private String apiName;
    /**
     * 数据发送者，发送者的partnerId
     */
    private String sender;
    /**
     * 数据接收者，接收者的partnerId
     */
    private String recipient;
    /**
     * 请求时间戳，发送方的请求时间，Unix时间戳，精确到毫秒
     */
    private long timestamp;
    /**
     * 是否带有业务数据
     */
    private boolean hasFile;
    /**
     * 业务数据文件格式
     */
    private String fileFormat;
    /**
     * 业务数据文件加密方式，如"PGP"：采用OpenPGP协议加密业务数据文件
     */
    private String fileEncryption;
    /**
     * 业务数据文件哈希值，业务数据文件的MD5值，用16进制字符表示
     */
    private String fileHashCode;
    /**
     * 源请求ID，可选，使用异常通知接口，或应答请求时，提供源请求的ID
     */
    private String originRequestId;
    /**
     * 错误代码，无错误为0
     */
    private int errorCode;
    /**
     * 元数据校验码，使用上述数据和发送者的“secretKey”计算的HMAC-SHA256值
     */
    private String mac;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isHasFile() {
        return hasFile;
    }

    public void setHasFile(boolean hasFile) {
        this.hasFile = hasFile;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileEncryption() {
        return fileEncryption;
    }

    public void setFileEncryption(String fileEncryption) {
        this.fileEncryption = fileEncryption;
    }

    public String getFileHashCode() {
        return fileHashCode;
    }

    public void setFileHashCode(String fileHashCode) {
        this.fileHashCode = fileHashCode;
    }

    public String getOriginRequestId() {
        return originRequestId;
    }

    public void setOriginRequestId(String originRequestId) {
        this.originRequestId = originRequestId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Metadata clone() {
        Metadata metadata = new Metadata();
        metadata.setRequestId(requestId);
        metadata.setReqType(reqType);
        metadata.setApiName(apiName);
        metadata.setSender(sender);
        metadata.setRecipient(recipient);
        metadata.setTimestamp(timestamp);
        metadata.setHasFile(hasFile);
        metadata.setFileFormat(fileFormat);
        metadata.setFileEncryption(fileEncryption);
        metadata.setFileHashCode(fileHashCode);
        metadata.setOriginRequestId(originRequestId);
        metadata.setErrorCode(errorCode);
        metadata.setMac(mac);
        return metadata;
    }

    public Metadata response() {
        Metadata metadata = new Metadata();
        metadata.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
        metadata.setReqType(Metadata.REQUEST_TYPE_RES);
        metadata.setApiName(apiName);
        metadata.setSender(recipient);
        metadata.setRecipient(sender);
        metadata.setTimestamp(System.currentTimeMillis());
        metadata.setOriginRequestId(requestId);
        metadata.setFileEncryption(fileEncryption);
        return metadata;
    }
}
