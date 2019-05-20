package com.etollpay.srpc.standard.etcchina.common;

import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * 对账明细信息
 */
public class BillChecking implements ICsvBean {

    /**
     * 发行方partnerId
     */
    private String issuerId;
    /**
     * 合作银行partnerId
     */
    private String bankId;
    /**
     * 发卡类型，11、常规卡；21、联营卡；
     */
    private int issueType;
    /**
     * 发行方收款账户，发行方的资金账户，微信为子商户号，银联为银行账号
     */
    private String collectionAccount;
    /**
     * 客户支付账户，微信为Openid，银联为银行账号
     */
    private String payAccount;
    /**
     * 账单编号，账单的唯一编号
     */
    private String billNo;
    /**
     * 支付平台账单编号，支付平台方的账单编号
     */
    private String bankBillNo;
    /**
     * 消费时间，YYYY-MM-DDTHH:mm:ss
     */
    private String tollTime;
    /**
     * 记账时间，YYYY-MM-DDTHH:mm:ss
     */
    private String storeTime;
    /**
     * 账单金额，单位：分
     */
    private long amount;
    /**
     * 交易类型，1、扣款；2、退款
     */
    private int type;
    /**
     * 代扣手续费
     */
    private long fee;
    /**
     * 手续费费率
     */
    private String rate;
    /**
     * 账单处理是否成功
     */
    private boolean success;
    /**
     * 账单处理失败时的描述信息
     */
    private String message;

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

    public int getIssueType() {
        return issueType;
    }

    public void setIssueType(int issueType) {
        this.issueType = issueType;
    }

    public String getCollectionAccount() {
        return collectionAccount;
    }

    public void setCollectionAccount(String collectionAccount) {
        this.collectionAccount = collectionAccount;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBankBillNo() {
        return bankBillNo;
    }

    public void setBankBillNo(String bankBillNo) {
        this.bankBillNo = bankBillNo;
    }

    public String getTollTime() {
        return tollTime;
    }

    public void setTollTime(String tollTime) {
        this.tollTime = tollTime;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getFields() {
        return new String[] {
                "issuerId",
                "bankId",
                "issueType",
                "collectionAccount",
                "payAccount",
                "billNo",
                "bankBillNo",
                "tollTime",
                "storeTime",
                "amount",
                "type",
                "fee",
                "rate",
                "success",
                "message"
        };
    }
}
