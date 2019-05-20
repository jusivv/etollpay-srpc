package com.etollpay.srpc.standard.etcchina.issuer;

/**
 * 快递信息跟踪请求
 */
public class ExpressTrackRequest {
    /**
     * 快递公司代号
     */
    private String expressCode;
    /**
     * 快递单号
     */
    private String waybillCode;

    public ExpressTrackRequest(String expressCode, String waybillCode) {
        this.expressCode = expressCode;
        this.waybillCode = waybillCode;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getWaybillCode() {
        return waybillCode;
    }

    public void setWaybillCode(String waybillCode) {
        this.waybillCode = waybillCode;
    }
}
