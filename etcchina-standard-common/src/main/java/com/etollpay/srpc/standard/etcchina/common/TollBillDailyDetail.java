package com.etollpay.srpc.standard.etcchina.common;

import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * 日结账单明细信息
 */
public class TollBillDailyDetail implements ICsvBean {

    /**
     * 账单编号，该明细所属账单的账单编号
     */
    private String billNo;
    /**
     * 消费时间，YYYY-MM-DDTHH:mm:ss
     */
    private String consumptionTime;
    /**
     * 消费说明
     * 封闭式公路：“入口时间，入口路·站 – 出口时间，出口路·站”；
     * 开放式公路：“时间，收费站名称”；
     * 停车场：“时间，停车场名称”；
     */
    private String description;
    /**
     * 记账时间，发行方的系统记账时间，YYYY-MM-DDTHH:mm:ss
     */
    private String chargeTime;
    /**
     * 消费金额，单位：分
     */
    private long fee;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String[] getFields() {
        return new String[] {
                "billNo",
                "consumptionTime",
                "description",
                "chargeTime",
                "fee"
        };
    }
}
