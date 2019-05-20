package com.etollpay.srpc.standard.etcchina.common;

import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * 发行结果索引信息
 */
public class EtcIssueResultIndex implements ICsvBean {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 寄送时间，YYYY-MM-DDTHH:mm:ss
     */
    private String deliveryTime;
    /**
     * 快递公司代码
     */
    private String expressCorpCode;
    /**
     * 快递公司名称
     */
    private String expressCorpName;
    /**
     * 运单编号
     */
    private String waybillNo;
    /**
     * 客户卡信息文件名
     */
    private String cardInfoFile;
    /**
     * 客户OBU信息文件名
     */
    private String obuInfoFile;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getExpressCorpCode() {
        return expressCorpCode;
    }

    public void setExpressCorpCode(String expressCorpCode) {
        this.expressCorpCode = expressCorpCode;
    }

    public String getExpressCorpName() {
        return expressCorpName;
    }

    public void setExpressCorpName(String expressCorpName) {
        this.expressCorpName = expressCorpName;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getCardInfoFile() {
        return cardInfoFile;
    }

    public void setCardInfoFile(String cardInfoFile) {
        this.cardInfoFile = cardInfoFile;
    }

    public String getObuInfoFile() {
        return obuInfoFile;
    }

    public void setObuInfoFile(String obuInfoFile) {
        this.obuInfoFile = obuInfoFile;
    }

    public String[] getFields() {
        return new String[] {
                "orderNo",
                "deliveryTime",
                "expressCorpCode",
                "expressCorpName",
                "waybillNo",
                "cardInfoFile",
                "obuInfoFile"
        };
    }
}
