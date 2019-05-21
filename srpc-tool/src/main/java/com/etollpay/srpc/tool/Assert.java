package com.etollpay.srpc.tool;


import com.etollpay.srpc.standard.basic.IntfError;

public class Assert {
    public static void is(boolean expr, ServiceException e) {
        if (expr) {
            throw e;
        }
    }

    public static void is(boolean expr, int errorCode, String message) {
        is(expr, new ServiceException(message, errorCode));
    }

    public static void is(boolean expr, IntfError error, String ... params) {
        is(expr, new ServiceException(error, params));
    }
}
