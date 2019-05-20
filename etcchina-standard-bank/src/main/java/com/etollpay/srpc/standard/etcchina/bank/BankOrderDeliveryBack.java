package com.etollpay.srpc.standard.etcchina.bank;

/**
 * Created by zhangxuhua on 2018/9/20.
 * 银行（合作方）订单同步数据  返回数据
 */
public class BankOrderDeliveryBack {
    private String orderId;
    private String etcOrderId;
    private boolean newUser;
    private String userId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEtcOrderId() {
        return etcOrderId;
    }

    public void setEtcOrderId(String etcOrderId) {
        this.etcOrderId = etcOrderId;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
