package com.etollpay.srpc.standard.etcchina.issuer;


import com.etollpay.srpc.standard.basic.ICsvBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangxuhua on 2018/9/21.
 */
public class IssuerTollBillInfo implements ICsvBean {

    @NotNull
    @Size(max = 50)
    private String serialNo;       // 交易流水号
    @NotNull
    @Size(max = 32)
    private String issuerId;       // 发行方编号
    @NotNull
    @Size(max = 32)
    private String bankId;          // 代扣银行编号
    @NotNull
    @Size(max = 32)
    private String contractId;     // 委托代扣协议编号

    @Min(0)
    @Max(Long.MAX_VALUE)
    private long amount;          // 消费金额，单位：分

    @NotNull
    @Size(max = 20)
    private String userId;        // 发行方--客户编号，17位数字，参见编码规则

    @NotNull
    @Size(max = 20)
    private String cardNo;        // ETC卡号，20位卡号

    @NotNull
    @Size(max = 20)
    private String vehicleId;     // 车辆编号，“<车牌号码>_<车牌颜色编号>”，如“京A12345_1”

    @NotNull
    @Size(max = 20)
    private String consumptionTime;    // 消费时间，YYYY-MM-DDTHH:mm:ss

    @NotNull
    @Size(max = 1000)
    private String description;      // 消费说明

    @NotNull
    @Size(max = 20)
    private String chargeTime;       // 发行方记账时间，YYYY-MM-DDTHH:mm:ss


    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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

    public String getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(String consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String[] getFields() {
        return new String[]{
                "serialNo",
                "issuerId",
                "bankId",
                "contractId",
                "amount",
                "userId",
                "cardNo",
                "vehicleId",
                "consumptionTime",
                "description",
                "chargeTime"
        };
    }
}
