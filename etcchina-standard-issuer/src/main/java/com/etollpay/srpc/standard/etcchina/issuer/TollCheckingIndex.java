package com.etollpay.srpc.standard.etcchina.issuer;

import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by zhngdawei on 2018-11-06.
 * 对账单索引文件内容
 */
public class TollCheckingIndex implements ICsvBean {
    private String issuerId;
    private String bankId;
    private String tallyDate;
    private String paymentDate;
    private int count;
    private long amount;
    private long fee;
    private String billFile;

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getBillFile() {
        return billFile;
    }

    public void setBillFile(String billFile) {
        this.billFile = billFile;
    }

    public String[] getFields() {
        return new String[]{
                "issuerId",
                "bankId",
                "tallyDate",
                "paymentDate",
                "count",
                "amount",
                "fee",
                "billFile"
        };
    }
}
