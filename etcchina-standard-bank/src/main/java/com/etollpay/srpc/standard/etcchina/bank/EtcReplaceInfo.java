package com.etollpay.srpc.standard.etcchina.bank;

/**
 * Created by zhangxuhua on 2018/11/7.
 */
public class EtcReplaceInfo implements IBankBiz {

    private String userId;
    private String contractId;
    private String issuerId;
    private Integer bizScope;

    private String bizTime;
    private String etcCardNo;
    private String cardBrand;
    private String obuNo;
    private String obuBrand;

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

    public Integer getBizScope() {
        return bizScope;
    }

    public void setBizScope(Integer bizScope) {
        this.bizScope = bizScope;
    }

    public String getBizTime() {
        return bizTime;
    }

    public void setBizTime(String bizTime) {
        this.bizTime = bizTime;
    }

    public String getEtcCardNo() {
        return etcCardNo;
    }

    public void setEtcCardNo(String etcCardNo) {
        this.etcCardNo = etcCardNo;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getObuNo() {
        return obuNo;
    }

    public void setObuNo(String obuNo) {
        this.obuNo = obuNo;
    }

    public String getObuBrand() {
        return obuBrand;
    }

    public void setObuBrand(String obuBrand) {
        this.obuBrand = obuBrand;
    }
}
