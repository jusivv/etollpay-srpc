package com.etollpay.srpc.standard.etcchina.issuer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 快递跟踪响应信息
 */
public class ExpressTrackResponse {

    /**
     * 快递状态：已完成（快递跟踪信息不会再发生变化）
     */
    public static final int EXPRESS_STATUS_FINISHED = 1;
    /**
     * 快递状态：在途
     */
    public static final int EXPRESS_STATUS_TRANSPORTING = 2;

    /**
     * 快递公司编号
     */
    @NotNull
    @Size(max = 10)
    private String expressCode;
    /**
     * 快递公司名称
     */
    @NotNull
    @Size(max = 50)
    private String expressName;
    /**
     * 快递单号
     */
    @NotNull
    @Size(max = 50)
    private String waybillCode;
    /**
     * 快递状态
     */
    @Min(0)
    @Max(9)
    private int expressStatus;
    /**
     * 快递出发地
     */
    @Size(max = 300)
    private String expressFrom;
    /**
     * 快递目的地
     */
    @Size(max = 300)
    private String expressTo;
    /**
     * 快递轨迹信息
     */
    private ExpressTrackDetail[] expressTrack;

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getWaybillCode() {
        return waybillCode;
    }

    public void setWaybillCode(String waybillCode) {
        this.waybillCode = waybillCode;
    }

    public int getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(int expressStatus) {
        this.expressStatus = expressStatus;
    }

    public String getExpressFrom() {
        return expressFrom;
    }

    public void setExpressFrom(String expressFrom) {
        this.expressFrom = expressFrom;
    }

    public String getExpressTo() {
        return expressTo;
    }

    public void setExpressTo(String expressTo) {
        this.expressTo = expressTo;
    }

    public ExpressTrackDetail[] getExpressTrack() {
        return expressTrack;
    }

    public void setExpressTrack(ExpressTrackDetail[] expressTrack) {
        this.expressTrack = expressTrack;
    }
}
