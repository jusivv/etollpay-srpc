package com.etollpay.srpc.standard.etcchina.bank;

import com.etollpay.srpc.standard.basic.ICsvBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhandgawei on 2018-09-19.
 */
public class BankBlackListInfo implements ICsvBean {

    /*
    *  合作方编号（partnerId）
    */
    @NotNull
    @Size(max = 32)
    private String bankId;
    /*
    *  ETC卡号，20位
    */
    @NotNull
    @Size(max = 50)
    private String etcCardNo;
    /*
    * 发行方编号（partnerId）
    */
    @NotNull
    @Size(max = 32)
    private String issuerId;
    /*
    * 申请时间戳
    */
    @Min(0)
    @Max(Long.MAX_VALUE)
    private long applyTime;

    /*
    *  1-卡挂失
    *  2-无卡挂起
    *  3-无卡注销
    *  4-欠费
    *  5-合作机构黑名单
    *  6-车型不符
    *
     */
    @Min(1)
    @Max(6)
    private int type;

    /*
    * 状态：1-进入黑名单 2-解除黑名单
    */
    @Min(1)
    @Max(2)
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
