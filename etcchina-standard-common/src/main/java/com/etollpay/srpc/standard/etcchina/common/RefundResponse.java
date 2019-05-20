package com.etollpay.srpc.standard.etcchina.common;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * 退款结果响应信息
 */
public class RefundResponse implements ICsvBean {

    /**
     * 发行方partnerId
     */
    private String partnerId;
    /**
     * 发卡类型，11、常规卡；21、联营卡；
     */
    private int issueType;
    /**
     * 代扣银行编号，即合作方partnerId
     */
    private String bankId;
    /**
     * 收款账户，发行方资金账户，微信为子商户号，银联为银行账号
     */
    private String collectionAccount;
    /**
     * 客户支付账户，微信为Openid，银联为银行账号
     */
    private String payAccount;
    /**
     * 委托代扣协议Id，微信为必要项，银联可不填
     */
    private String contractId;
    /**
     * 退款账单编号，发行方为该笔退款账单指定的编号，需保证全局唯一，推荐使用“<userId>@<自行编号>”的格式
     */
    private String refundBillNo;
    /**
     * 银行方退款订单编号
     */
    private String bankRefundBillNo;
    /**
     * 退款金额，实际的退款金额，单位：分
     */
    private long refundAmount;
    /**
     * 银行方的消费账单编号，发生退款行为的银行方消费账单唯一编号
     */
    private String bankBillNo;
    /**
     * 消费账单金额，发生退款行为的消费账单的原金额，单位：分
     */
    private long billAmount;
    /**
     * 退款时间，YYYY-MM-DDTHH:mm:ss
     */
    private String refundTime;
    /**
     * 退款是否成功
     */
    private boolean success;
    /**
     * 退款失败后的描述信息
     */
    private String message;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public int getIssueType() {
        return issueType;
    }

    public void setIssueType(int issueType) {
        this.issueType = issueType;
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

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getRefundBillNo() {
        return refundBillNo;
    }

    public void setRefundBillNo(String refundBillNo) {
        this.refundBillNo = refundBillNo;
    }

    public String getBankRefundBillNo() {
        return bankRefundBillNo;
    }

    public void setBankRefundBillNo(String bankRefundBillNo) {
        this.bankRefundBillNo = bankRefundBillNo;
    }

    public long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getBankBillNo() {
        return bankBillNo;
    }

    public void setBankBillNo(String bankBillNo) {
        this.bankBillNo = bankBillNo;
    }

    public long getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(long billAmount) {
        this.billAmount = billAmount;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
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
                "partnerId",
                "issueType",
                "bankId",
                "collectionAccount",
                "payAccount",
                "contractId",
                "refundBillNo",
                "bankRefundBillNo",
                "refundAmount",
                "bankBillNo",
                "billAmount",
                "refundTime",
                "success",
                "message"
        };
    }
}
