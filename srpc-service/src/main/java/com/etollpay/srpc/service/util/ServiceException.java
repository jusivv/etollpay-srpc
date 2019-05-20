package com.etollpay.srpc.service.util;


import com.etollpay.srpc.standard.basic.IntfError;

public class ServiceException extends RuntimeException {
    private int errorCode;

    public ServiceException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ServiceException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(Throwable cause, String message, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ServiceException(IntfError error, String... params) {
        this(parseMessage(error.getMessage(), params), error.getCode());
    }

    public ServiceException(Throwable cause, IntfError error, String... params) {
        this(cause, parseMessage(error.getMessage(), params), error.getCode());
    }

    public static String parseMessage(String message, String... params) {
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                sb.append("[").append(params[i]).append("]");
            }
        }
        return message + " " + sb.toString();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String toJSON() {
        return "{\"errorCode\": " + errorCode + ", \"message\": \"" + this.getLocalizedMessage() + "\"}";
    }
}
