package com.etollpay.srpc.invoker;

import com.etollpay.srpc.standard.basic.Metadata;

public class ResponseMessage {
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 响应的元数据
     */
    private Metadata resMetadata;
    /**
     * 响应密文，可用作存证
     */
    private byte[] cipherData;
    /**
     * 响应明文，用作业务处理
     */
    private byte[] plainData;

    public ResponseMessage(int statusCode, Metadata resMetadata, byte[] cipherData, byte[] plainData) {
        this.statusCode = statusCode;
        this.resMetadata = resMetadata;
        this.cipherData = cipherData;
        this.plainData = plainData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Metadata getResMetadata() {
        return resMetadata;
    }

    public void setResMetadata(Metadata resMetadata) {
        this.resMetadata = resMetadata;
    }

    public byte[] getCipherData() {
        return cipherData;
    }

    public void setCipherData(byte[] cipherData) {
        this.cipherData = cipherData;
    }

    public byte[] getPlainData() {
        return plainData;
    }

    public void setPlainData(byte[] plainData) {
        this.plainData = plainData;
    }
}
