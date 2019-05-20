package com.etollpay.srpc.standard.etcchina.issuer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2018-09-20.
 */
public class CustomerObuInfo {

    /*
     * OBU序号编码，参见编码规则
     */
    @NotNull
    @Size(max = 12)
    private String  id;
    /*
     * OBU网络编号，参见编码规则
     */
    @NotNull
    @Size(max = 4)
    private String  netId;
    /*
     * OBU品牌，参见编码规则
     */
    @Min(1)
    @Max(999)
    private int  brand;
    /*
     * OBU型号
     */
    @Size(max = 100)
    private String  model;
    /*
     * 客户编号，17位数字，参见编码规则
     */
    @NotNull
    @Size(max = 20)
    private String  userId;
    /*
     * 车辆编号，“<车牌号码>_<车牌颜色编号>”，如“京A12345_1”
     */
    @NotNull
    @Size(max = 20)
    private String  vehicleId;
    /*
     * 收费车型，参见编码规则
     */
    @Min(1)
    @Max(99)
    private int  vehicleType;
    /*
     * OBU启用时间，YYYY-MM-DDTHH:mm:ss
     */
    @NotNull
    @Size(max = 20)
    private String  enableTime;
    /*
     * OBU到期时间，YYYY-MM-DDTHH:mm:ss
     */
    @NotNull
    @Size(max = 20)
    private String  expireTime;

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

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
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
}
