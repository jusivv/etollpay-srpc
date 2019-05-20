package com.etollpay.srpc.standard.etcchina.common;

public class AbstractTollBill {
    /**
     * 发行方partnerId
     */
    private String partnerId;
    /**
     * 发卡类型，11、常规卡；21、联营卡
     */
    private int issueType;
    /**
     * 代扣银行编号，即合作方partnerId
     */
    private String bankId;
    /**
     * 发行方收款账户，微信为子商户号，银联为银行账号
     */
    private String collectionAccount;
    /**
     * 客户支付账户，微信为Openid，银联为银行账号
     */
    private String payAccount;
    /**
     * 委托代扣协议Id，银联可不填
     */
    private String contractId;
    /**
     * 账单编号，发行方为该笔代扣账单指定的编号，需保证全局唯一，推荐使用“<partnerId>@<自行编号>”的格式
     */
    private String billNo;
    /**
     * 账单金额，单位：分
     */
    private long billAmount;
    /**
     * 客户编号，17位数字，参见编码规则
     */
    private String userId;
    /**
     * ETC卡号，20位卡号
     */
    private String cardNo;
    /**
     * 车辆编号，“<车牌号码>_<车牌颜色编号>”，如“京A12345_1”
     */
    private String vehicleId;

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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public long getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(long billAmount) {
        this.billAmount = billAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
