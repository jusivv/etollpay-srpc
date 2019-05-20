package com.etollpay.srpc.standard.etcchina.bank;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by zhangxuhua on 2018/11/8.
 */
public class BankPaymentInfo implements ICsvBean {

    private String serialNo;
    private String userId;
    private String contractId;
    private String issuerId;
    private String etcCardNo;
    private long amount;
    private String payTime;
    private String tallyDate;
    private String batchNo;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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

    public String getEtcCardNo() {
        return etcCardNo;
    }

    public void setEtcCardNo(String etcCardNo) {
        this.etcCardNo = etcCardNo;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTallyDate() {
        return tallyDate;
    }

    public void setTallyDate(String tallyDate) {
        this.tallyDate = tallyDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String[] getFields() {
        return new String[]{
                "serialNo",
                "userId",
                "contractId",
                "issuerId",
                "etcCardNo",
                "amount",
                "payTime",
                "tallyDate",
                "batchNo"
        };
    }


}
