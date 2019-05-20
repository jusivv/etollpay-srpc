package com.etollpay.srpc.standard.basic;

/**
 * 通知接口的异常响应信息
 */
public class NotifyError extends Metadata {
    /**
     * 异常描述信息
     */
    private String errorMessage;

    public NotifyError(Metadata metadata, String errorMessage) {
        this.setRequestId(metadata.getRequestId());
        this.setReqType(metadata.getReqType());
        this.setApiName(metadata.getApiName());
        this.setSender(metadata.getSender());
        this.setRecipient(metadata.getRecipient());
        this.setTimestamp(metadata.getTimestamp());
        this.setHasFile(metadata.isHasFile());
        this.setFileFormat(metadata.getFileFormat());
        this.setFileEncryption(metadata.getFileEncryption());
        this.setFileHashCode(metadata.getFileHashCode());
        this.setOriginRequestId(metadata.getOriginRequestId());
        this.setErrorCode(metadata.getErrorCode());
        this.setMac(metadata.getMac());
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
