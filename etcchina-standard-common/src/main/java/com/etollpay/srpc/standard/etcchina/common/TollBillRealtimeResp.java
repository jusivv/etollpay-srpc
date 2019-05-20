package com.etollpay.srpc.standard.etcchina.common;

/**
 * 实时账单同步接口响应信息
 */
public class TollBillRealtimeResp extends AbstractTollBill  {

    /**
     * 消费时间，YYYY-MM-DDTHH:mm:ss
     */
    private String consumptionTime;
    /**
     * 记账时间，YYYY-MM-DDTHH:mm:ss
     */
    private String chargeTime;
    /**
     * 扣款时间，YYYY-MM-DDTHH:mm:ss
     */
    private String paymentTime;
    /**
     * 扣款结果，是否成功
     */
    private boolean paymentResult;
    /**
     * 扣款失败时的描述信息
     */
    private String paymentMsg;

    public String getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(String consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public boolean isPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(boolean paymentResult) {
        this.paymentResult = paymentResult;
    }

    public String getPaymentMsg() {
        return paymentMsg;
    }

    public void setPaymentMsg(String paymentMsg) {
        this.paymentMsg = paymentMsg;
    }
}
