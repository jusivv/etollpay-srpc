package com.etollpay.srpc.standard.etcchina.common;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * 卡签更换信息索引
 */
public class EtcCardObuUpdateIndex implements ICsvBean {
    /**
     * 客户编号，17位数字，参见编码规则
     */
    private String userId;
    /**
     * 原订单号
     */
    private String orderNo;
    /**
     * 原卡片网络编号
     */
    private String cpuNetId;
    /**
     * 原CPU卡内部编号
     */
    private String cpuNum;
    /**
     * 原OBU网络编号
     */
    private String obuNetId;
    /**
     * 原OBU内部编号
     */
    private String obuNum;
    /**
     * 车辆编号，“<车牌号码>_<车牌颜色编号>”，如“京A12345_1”
     */
    private String vehicleId;
    /**
     * 更换范围：1、卡片；2、OBU；3、卡片+OBU
     */
    private int bizScope;
    /**
     * 业务办理时间，YYYY-MM-DDTHH:mm:ss
     */
    private String bizTime;
    /**
     * 新客户卡信息文件名
     */
    private String newCardFile;
    /**
     * 新客户OBU信息文件名
     */
    private String newObuFile;
    /**
     * 备注信息
     */
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCpuNetId() {
        return cpuNetId;
    }

    public void setCpuNetId(String cpuNetId) {
        this.cpuNetId = cpuNetId;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public String getObuNetId() {
        return obuNetId;
    }

    public void setObuNetId(String obuNetId) {
        this.obuNetId = obuNetId;
    }

    public String getObuNum() {
        return obuNum;
    }

    public void setObuNum(String obuNum) {
        this.obuNum = obuNum;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getBizScope() {
        return bizScope;
    }

    public void setBizScope(int bizScope) {
        this.bizScope = bizScope;
    }

    public String getBizTime() {
        return bizTime;
    }

    public void setBizTime(String bizTime) {
        this.bizTime = bizTime;
    }

    public String getNewCardFile() {
        return newCardFile;
    }

    public void setNewCardFile(String newCardFile) {
        this.newCardFile = newCardFile;
    }

    public String getNewObuFile() {
        return newObuFile;
    }

    public void setNewObuFile(String newObuFile) {
        this.newObuFile = newObuFile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getFields() {
        return new String[] {
                "userId",
                "orderNo",
                "cpuNetId",
                "cpuNum",
                "obuNetId",
                "obuNum",
                "vehicleId",
                "bizScope",
                "bizTime",
                "newCardFile",
                "newObuFile",
                "remark"
        };
    }
}
