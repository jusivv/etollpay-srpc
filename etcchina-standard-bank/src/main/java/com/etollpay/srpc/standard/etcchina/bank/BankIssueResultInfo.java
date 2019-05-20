package com.etollpay.srpc.standard.etcchina.bank;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by zhangxuhua on 2018/11/7.
 */
public class BankIssueResultInfo  implements IBankBiz, ICsvBean {
    private String userId;
    private String contractId;
    private String issuerId;
    private String issueTime;
    private String etcCardNo;
    private String cardBrand;
    private String obuNo;
    private String obuBrand;
    private String vehType;

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

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
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

    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public String[] getFields() {
        return new String[]{
                "userId",
                "contractId",
                "issuerId",
                "issueTime",
                "etcCardNo",
                "cardBrand",
                "obuNo",
                "obuBrand",
                "vehType"
        };
    }

}
