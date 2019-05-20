package com.etollpay.srpc.standard.etcchina.common;

/**
 * ETC卡片信息
 */
public class EtcCard {

    /**
     * 卡片网络编号
     */
    private String netId;
    /**
     * CPU卡内部编号
     */
    private String cpuNum;
    /**
     * 卡类型，参见编码规则
     */
    private int cardType;
    /**
     * 卡品牌，参见编码规则
     */
    private int brand;
    /**
     * 卡型号
     */
    private String model;
    /**
     * 客服合作机构编号，绑定账户所属合作机构
     */
    private String agencyId;
    /**
     * 客户编号，17位数字，参见编码规则
     */
    private String userId;
    /**
     * 车辆编号，“<车牌号码>_<车牌颜色编号>”，如“京A12345_1”
     */
    private String vehicleId;
    /**
     * 卡启用时间，YYYY-MM-DDTHH:mm:ss
     */
    private String enableTime;
    /**
     * 卡到期时间，YYYY-MM-DDTHH:mm:ss
     */
    private String expireTime;
    /**
     * 开卡时间，YYYY-MM-DDTHH:mm:ss
     */
    private String issuedTime;

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(String enableTime) {
        this.enableTime = enableTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }
}
