package com.etollpay.srpc.standard.etcchina.issuer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 快递轨迹信息
 */
public class ExpressTrackDetail {
    /**
     * 轨迹记录的日期时间，YYYY-MM-DD HH:mm:ss
     */
    @NotNull
    @Size(max = 20)
    private String dateTime;
    /**
     * 轨迹记录内容
     */
    @NotNull
    @Size(max = 300)
    private String content;
    /**
     * 快递当时所在地信息
     */
    @Size(max = 50)
    private String zone;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
