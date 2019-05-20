package com.etollpay.srpc.standard.etcchina.common;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * 对账索引信息
 */
public class BillCheckingIndex implements ICsvBean {

    /**
     * 发行方partnerId
     */
    private String issuerId;
    /**
     * 合作渠道的partnerId
     */
    private String bankId;
    /**
     * 发行方收款账户，发行方的资金账户，微信为子商户号，银联为银行账号
     */
    private String collectionAccount;
    /**
     * 对账单日期，YYYY-MM-DD
     */
    private String checkingDate;
    /**
     * 对账单中的交易笔数
     */
    private long count;
    /**
     * 对账单总收入金额，单位：分
     */
    private long income;
    /**
     * 对账单总支出金额，单位：分
     */
    private long outgo;
    /**
     * 对账单手续费总额，单位：分
     */
    private long fee;
    /**
     * 对账单文件名
     */
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

    public String getCollectionAccount() {
        return collectionAccount;
    }

    public void setCollectionAccount(String collectionAccount) {
        this.collectionAccount = collectionAccount;
    }

    public String getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(String checkingDate) {
        this.checkingDate = checkingDate;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public long getOutgo() {
        return outgo;
    }

    public void setOutgo(long outgo) {
        this.outgo = outgo;
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
        return new String[] {
                "issuerId",
                "bankId",
                "collectionAccount",
                "checkingDate",
                "count",
                "income",
                "outgo",
                "fee",
                "billFile"
        };
    }
}
