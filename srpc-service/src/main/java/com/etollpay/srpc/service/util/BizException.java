package com.etollpay.srpc.service.util;

public class BizException extends ServiceException {
    private String jsonString;

    public BizException(int errorCode, String jsonString) {
        super(errorCode);
        this.jsonString = jsonString;
    }

    public String getJsonString() {
        return jsonString;
    }
}
