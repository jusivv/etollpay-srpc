package com.etollpay.srpc.standard.etcchina.bank;

public class LoginResponse {
    /**
     * 状态码：登录成功
     */
    public static final int STATUS_CODE_SUCCESS = 0;
    /**
     * 状态码：无发行系统用户标识
     */
    public static final int STATUS_CODE_NONE_USER = 1;
    /**
     * 状态码：令牌过期或失效
     */
    public static final int STATUS_CODE_INVALID_TOKEN = 2;
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 发行系统用户标识
     */
    private String userId;
    /**
     * 申请用户的身份证姓名
     */
    private String idName;
    /**
     * 申请用户的身份证号码
     */
    private String idNo;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
