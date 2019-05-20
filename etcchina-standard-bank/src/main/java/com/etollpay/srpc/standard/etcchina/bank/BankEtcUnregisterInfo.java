package com.etollpay.srpc.standard.etcchina.bank;

import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by zhangxuhua on 2018/11/8.
 */
public class BankEtcUnregisterInfo implements IBankBiz, ICsvBean {

    private String userId;
    private String contractId;
    private String issuerId;
    private String etcCardNo;
    private String unregisterTime;

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

    public String getEtcCardNo() {
        return etcCardNo;
    }

    public void setEtcCardNo(String etcCardNo) {
        this.etcCardNo = etcCardNo;
    }

    public String getUnregisterTime() {
        return unregisterTime;
    }

    public void setUnregisterTime(String unregisterTime) {
        this.unregisterTime = unregisterTime;
    }

    public String[] getFields() {
        return new String[]{
                "userId",
                "contractId",
                "issuerId",
                "etcCardNo",
                "unregisterTime"
        };
    }

}
