package com.etollpay.srpc.standard.etcchina.issuer;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by zhangdawei on 2018-10-31.
 * 黑名单申请接口文件信息
 */
public class BlackListReqInfo implements ICsvBean {
    private String bankId;
    private String etcCardNo;
    private String issuerId;
    private long applyTime;
    private int type;
    private int status;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getEtcCardNo() {
        return etcCardNo;
    }

    public void setEtcCardNo(String etcCardNo) {
        this.etcCardNo = etcCardNo;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(long applyTime) {
        this.applyTime = applyTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getFields() {
        return new String[]{
                "bankId",
                "etcCardNo",
                "issuerId",
                "applyTime",
                "type",
                "status"
        };
    }
}
