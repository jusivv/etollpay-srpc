package com.etollpay.srpc.standard.etcchina.common;

/**
 * 实时账单同步数据
 */
public class TollBillRealtime extends AbstractTollBill {

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
     * 记账时间，YYYY-MM-DDTHH:mm:ss
     */
    private String chargeTime;

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
}
