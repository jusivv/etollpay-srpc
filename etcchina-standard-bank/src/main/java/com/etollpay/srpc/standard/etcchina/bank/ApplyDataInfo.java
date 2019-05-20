package com.etollpay.srpc.standard.etcchina.bank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangdawei on 2018-09-25.
 */
public class ApplyDataInfo {
    @Size(max = 32)
    private String orderId;
    @NotNull
    @Size(max = 32)
    private String etcOrderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEtcOrderId() {
        return etcOrderId;
    }

    public void setEtcOrderId(String etcOrderId) {
        this.etcOrderId = etcOrderId;
    }
}
