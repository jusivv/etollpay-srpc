package com.etollpay.srpc.standard.etcchina.issuer;

import com.etollpay.srpc.standard.basic.ICsvBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangxuhua on 2018/9/28.
 */
public class EtcUnregisterInfo implements ICsvBean {

    @NotNull
    @Size(max = 32)
    private String issuerId;
    @NotNull
    @Size(max = 20)
    private String userId;
    @NotNull
    @Size(max = 32)
    private String bankId;
    @NotNull
    @Size(max = 32)
    private String contractId;
    @NotNull
    @Size(max = 4)
    private String cpuNetId;
    @NotNull
    @Size(max = 16)
    private String cpuNum;
    @NotNull
    @Size(max = 20)
    private String vehicleId;
    @NotNull
    @Size(max = 20)
    private String unregisterTime;

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCpuNetId() {
        return cpuNetId;
    }

    public void setCpuNetId(String cpuNetId) {
        this.cpuNetId = cpuNetId;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getUnregisterTime() {
        return unregisterTime;
    }

    public void setUnregisterTime(String unregisterTime) {
        this.unregisterTime = unregisterTime;
    }

    public String[] getFields() {
        return new String[]{
                "issuerId",
                "userId",
                "bankId",
                "contractId",
                "cpuNetId",
                "cpuNum",
                "vehicleId",
                "unregisterTime"
        };
    }


}
