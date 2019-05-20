package com.etollpay.srpc.standard.etcchina.bank;

import com.etollpay.srpc.standard.basic.ICsvBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangxuhua on 2018/9/26.
 */
public class BankComplainInfo implements ICsvBean {

    @NotNull
    @Size(max = 50)
    private String orderNo;              // 工单编号

    @NotNull
    @Size(max = 32)
    private String bankId;               // 合作方编号

    @NotNull
    @Size(max = 50)
    private String userId;                // 投诉用户在发行系统中的userId

    @NotNull
    @Size(max = 50)
    private String username;              // 投诉用户姓名

    @Size(max = 32)
    private String contractId;           // 投诉用户代扣协议编号

    @Size(max = 32)
    private String issuerId;               // 投诉用户所属发行方编号

    @NotNull
    @Size(max = 20)
    private String tel;                    // 投诉用户联系电话

    @NotNull
    @Size(max = 20)
    private String complainTime;          // 投诉时间，YYYY-MM-DDTHH:MM:SS

    @NotNull
    @Size(max = 1000)
    private String remark;                  //投诉内容简述


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getComplainTime() {
        return complainTime;
    }

    public void setComplainTime(String complainTime) {
        this.complainTime = complainTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getFields() {
        return new String[]{
                "orderNo",
                "bankId",
                "userId",
                "username",
                "contractId",
                "issuerId",
                "tel",
                "complainTime",
                "remark"
        };
    }
}
