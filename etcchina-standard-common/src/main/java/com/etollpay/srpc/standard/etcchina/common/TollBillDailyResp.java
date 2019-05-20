package com.etollpay.srpc.standard.etcchina.common;


import com.etollpay.srpc.standard.basic.ICsvBean;

public class TollBillDailyResp extends TollBillDaily implements ICsvBean {

    /**
     * 扣款时间，YYYY-MM-DDTHH:mm:ss
     */
    private String paymentTime;
    /**
     * 扣款是否成功
     */
    private boolean paymentResult;
    /**
     * 扣款失败时的描述信息
     */
    private String paymentMsg;

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

    @Override
    public String[] getFields() {
        return new String[] {
                "partnerId",
                "issueType",
                "bankId",
                "collectionAccount",
                "payAccount",
                "contractId",
                "billNo",
                "bankBillNo",
                "billAmount",
                "userId",
                "cardNo",
                "vehicleId",
                "billDate",
                "paymentTime",
                "paymentResult",
                "paymentMsg"
        };
    }
}
