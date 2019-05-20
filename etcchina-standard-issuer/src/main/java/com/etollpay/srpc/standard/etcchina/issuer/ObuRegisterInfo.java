package com.etollpay.srpc.standard.etcchina.issuer;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by Administrator on 2018-09-21.
 */
public class ObuRegisterInfo implements ICsvBean {

    /*
     * 车辆编号，参见编码规则
     */
    private String vehicleId;
    /*
     * OBU序号编码，参见编码规则
     */
    private String id;
    /*
     * OBU网络编号，参见编码规则
     */
    private String netId;
    /*
     * 注册时间，YYYY-MM-DDTHH:mm:ss
     */
    private String registeredTime;
    /*
     * OBU安装方式，参见编码规则，如：1-自行安装、2-网点安装
     */
    private int installType;
    /*
     * OBU安装/激活地点。若OBU安装方式为网点安装，请填写服务网点编号；若安装方式为自行安装，请填写”0”
     */
    private String installChannelId;
    /*
     * OBU安装／激活时间，YYYY-MM-DDTHH:mm:ss
     */
    private String installTime;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    public int getInstallType() {
        return installType;
    }

    public void setInstallType(int installType) {
        this.installType = installType;
    }

    public String getInstallChannelId() {
        return installChannelId;
    }

    public void setInstallChannelId(String installChannelId) {
        this.installChannelId = installChannelId;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String[] getFields() {
        return new String[]{
            "vehicleId",
            "id",
            "netId",
            "registeredTime",
            "installType",
            "installChannelId",
            "installTime"
        };
    }
}
