package com.etollpay.srpc.standard.etcchina.bank;


/**
 * Created by zhangxuhua on 2018/11/8.
 */
public class BankObuActivedInfo implements IBankBiz {
    private String userId;
    private String contractId;
    private String issuerId;
    private String obuNo;
    private String activeTime;

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

    public String getObuNo() {
        return obuNo;
    }

    public void setObuNo(String obuNo) {
        this.obuNo = obuNo;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }
}
