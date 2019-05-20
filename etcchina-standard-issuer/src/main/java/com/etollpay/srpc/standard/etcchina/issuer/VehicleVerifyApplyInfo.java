package com.etollpay.srpc.standard.etcchina.issuer;

/**
 * Created by zhandgawei on 2018-11-06.
 * 车辆信息验证接口申请数据内容
 */
public class VehicleVerifyApplyInfo {
    private String plateNum;
    private String ownerName;
    private String vehicleType;
    private String useCharacter;
    private String vin;
    private String approvedCount;
    private String vehicleModel;
    private String engineNum;
    private String registerDate;
    private String permittedWeight;
    private String totalMess;
    private String maintenanceMess;
    private String outsideDimensions;
    private String permittedTowWeight;

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getUseCharacter() {
        return useCharacter;
    }

    public void setUseCharacter(String useCharacter) {
        this.useCharacter = useCharacter;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getApprovedCount() {
        return approvedCount;
    }

    public void setApprovedCount(String approvedCount) {
        this.approvedCount = approvedCount;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getPermittedWeight() {
        return permittedWeight;
    }

    public void setPermittedWeight(String permittedWeight) {
        this.permittedWeight = permittedWeight;
    }

    public String getTotalMess() {
        return totalMess;
    }

    public void setTotalMess(String totalMess) {
        this.totalMess = totalMess;
    }

    public String getMaintenanceMess() {
        return maintenanceMess;
    }

    public void setMaintenanceMess(String maintenanceMess) {
        this.maintenanceMess = maintenanceMess;
    }

    public String getOutsideDimensions() {
        return outsideDimensions;
    }

    public void setOutsideDimensions(String outsideDimensions) {
        this.outsideDimensions = outsideDimensions;
    }

    public String getPermittedTowWeight() {
        return permittedTowWeight;
    }

    public void setPermittedTowWeight(String permittedTowWeight) {
        this.permittedTowWeight = permittedTowWeight;
    }
}
