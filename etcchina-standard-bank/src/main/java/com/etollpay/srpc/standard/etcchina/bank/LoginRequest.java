package com.etollpay.srpc.standard.etcchina.bank;

/**
 * 银行登录认证信息
 */
public class LoginRequest {
    /**
     * 令牌
     */
    private String token;

    public LoginRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
