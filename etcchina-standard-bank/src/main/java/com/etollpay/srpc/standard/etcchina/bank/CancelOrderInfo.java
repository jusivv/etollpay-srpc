package com.etollpay.srpc.standard.etcchina.bank;

/**
 * Created by zhangxuhua on 2018/10/29.
 */
public class CancelOrderInfo implements IBankBiz {
    private String etcOrderId;
    private String userId;
    private String contractId;
    private String issuerId;
    private String cancelTime;

    public String getEtcOrderId() {
        return etcOrderId;
    }

    public void setEtcOrderId(String etcOrderId) {
        this.etcOrderId = etcOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }
}
