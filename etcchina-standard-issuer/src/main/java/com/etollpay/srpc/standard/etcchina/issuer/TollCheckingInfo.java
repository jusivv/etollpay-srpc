package com.etollpay.srpc.standard.etcchina.issuer;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by zhangdawei on 2018-11-06.
 * 渠道对账单文件内容
 */
public class TollCheckingInfo implements ICsvBean {
    private String serialNo;
    private String bankId;
    private String userId;
    private String contractId;
    private String issuerId;
    private String etcCardNo;
    private int type;
    private String tradeTime;
    private long amount;
    private long fee;
    private String rate;
    private String tallyDate;
    private String paymentDate;
    private boolean success;
    private String remark;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTallyDate() {
        return tallyDate;
    }

    public void setTallyDate(String tallyDate) {
        this.tallyDate = tallyDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getFields() {
        return new String[]{
                "serialNo",
                "bankId",
                "userId",
                "contractId",
                "issuerId",
                "etcCardNo",
                "type",
                "tradeTime",
                "amount",
                "fee",
                "rate",
                "tallyDate",
                "paymentDate",
                "success",
                "remark"
        };
    }
}
