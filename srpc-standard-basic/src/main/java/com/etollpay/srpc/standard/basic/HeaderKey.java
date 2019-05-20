package com.etollpay.srpc.standard.basic;

public class HeaderKey {
    /**
     * 请求ID
     */
    public static final String REQUEST_ID = "X-MESSAGE-REQID";
    /**
     * 请求类别
     */
    public static final String REQUEST_TYPE = "X-MESSAGE-TYPE";
    /**
     * API名称
     */
    public static final String API_NAME = "X-MESSAGE-API";
    /**
     * 数据发送者
     */
    public static final String SENDER = "X-MESSAGE-SENDER";
    /**
     * 数据接收者
     */
    public static final String RECIPIENT = "X-MESSAGE-RECIPIENT";
    /**
     * 请求时间戳
     */
    public static final String TIMESTAMP = "X-MESSAGE-TIMESTAMP";
    /**
     * 是否带有业务数据
     */
    public static final String HAS_FILE = "X-MESSAGE-HAS-FILE";
    /**
     * 业务数据文件格式
     */
    public static final String FILE_FORMAT = "X-MESSAGE-FORMAT";
    /**
     * 业务数据文件加密方式
     */
    public static final String FILE_ENCRYPTION = "X-MESSAGE-ENCRYPTION";
    /**
     * 业务数据文件加密方式
     */
    public static final String FILE_HASH_CODE = "X-MESSAGE-HASH-CODE";
    /**
     * 源请求ID
     */
    public static final String ORIGIN_REQUEST_ID = "X-MESSAGE-ORIGIN-REQID";
    /**
     * 错误代码
     */
    public static final String ERROR_CODE = "X-MESSAGE-ERROR-CODE";
    /**
     * 元数据校验码
     */
    public static final String AUTHENTICATION_CODE = "X-MESSAGE-AUTHENTICATION-CODE";

    /**
     * Header List
     */
    public static final String[] LIST = new String[] {
            HeaderKey.REQUEST_ID,
            HeaderKey.REQUEST_TYPE,
            HeaderKey.API_NAME,
            HeaderKey.SENDER,
            HeaderKey.RECIPIENT,
            HeaderKey.TIMESTAMP,
            HeaderKey.HAS_FILE,
            HeaderKey.FILE_FORMAT,
            HeaderKey.FILE_ENCRYPTION,
            HeaderKey.FILE_HASH_CODE,
            HeaderKey.ORIGIN_REQUEST_ID,
            HeaderKey.ERROR_CODE,
            HeaderKey.AUTHENTICATION_CODE
    };

}
