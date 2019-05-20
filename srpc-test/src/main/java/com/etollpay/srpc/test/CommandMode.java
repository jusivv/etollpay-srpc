package com.etollpay.srpc.test;

public enum CommandMode {
    /**
     * 创建业务数据文件
     */
    CREATE_BIZ_FILE,
    /**
     * 发起纯HTTP协议请求
     */
    MESSAGE_REQUEST,
    /**
     * 发起混合协议的通知请求
     */
    NOTIFY_REQUEST
}
