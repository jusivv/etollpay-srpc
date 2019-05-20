package com.etollpay.srpc.standard.etcchina.issuer;

import com.etollpay.srpc.standard.basic.ICsvBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangdawei on 2018-09-20.
 */
public class IssueResultInfo  implements ICsvBean {

    /*
    *  业务类型 1、新办 2、卡更换 3、标签更换 4、卡签更换
    */
    @Min(1)
    @Max(4)
    private int type;
    /*
    * 客户卡信息文件名，业务类型为标签更换时可不填
    */
    @Size(max = 50)
    private String cardInfoFile;
    /*
    *  客户OBU信息文件名，业务类型为卡更换时可不填
    */
    @Size(max = 50)
    private String obuInfoFile;
    /*
    *  订单编号
    */
    @NotNull
    @Size(max = 32)
    private String orderNo;
    /*
    * 寄送时间，YYYY-MM-DDTHH:mm:ss
    */
    @Size(max = 20)
    private String deliveryTime;
    /*
    * 快递公司代码（另附）
    */
    @Size(max = 10)
    private String expressCorpCode;
    /*
    * 快递公司名称
    */
    @Size(max = 200)
    private String expressCorpName;
    /*
    * 运单编号
    */
    @Size(max = 50)
    private String waybillNo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String[] getFields() {
        return new String[]{
                "type",
                "cardInfoFile",
                "obuInfoFile",
                "orderNo",
                "deliveryTime",
                "expressCorpCode",
                "expressCorpName",
                "waybillNo"
        };
    }
}
