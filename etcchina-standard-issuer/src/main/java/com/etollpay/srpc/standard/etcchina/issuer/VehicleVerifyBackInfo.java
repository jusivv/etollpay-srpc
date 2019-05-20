package com.etollpay.srpc.standard.etcchina.issuer;

/**
 * Created by zhangdawei on 2018-11-06.
 * 车辆信息校验接口返回结果
 */
public class VehicleVerifyBackInfo {
    private int result;
    private int plateNum;
    private int ownerName;
    private int vehicleType;
    private int useCharacter;
    private int vin;
    private int approvedCount;
    private int vehicleModel;
    private int engineNum;
    private int registerDate;
    private int permittedWeight;
    private int totalMess;
    private int maintenanceMess;
    private int outsideDimensions;
    private int permittedTowWeight;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(int plateNum) {
        this.plateNum = plateNum;
    }

    public int getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(int ownerName) {
        this.ownerName = ownerName;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getUseCharacter() {
        return useCharacter;
    }

    public void setUseCharacter(int useCharacter) {
        this.useCharacter = useCharacter;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getApprovedCount() {
        return approvedCount;
    }

    public void setApprovedCount(int approvedCount) {
        this.approvedCount = approvedCount;
    }

    public int getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(int vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(int engineNum) {
        this.engineNum = engineNum;
    }

    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(int registerDate) {
        this.registerDate = registerDate;
    }

    public int getPermittedWeight() {
        return permittedWeight;
    }

    public void setPermittedWeight(int permittedWeight) {
        this.permittedWeight = permittedWeight;
    }

    public int getTotalMess() {
        return totalMess;
    }

    public void setTotalMess(int totalMess) {
        this.totalMess = totalMess;
    }

    public int getMaintenanceMess() {
        return maintenanceMess;
    }

    public void setMaintenanceMess(int maintenanceMess) {
        this.maintenanceMess = maintenanceMess;
    }

    public int getOutsideDimensions() {
        return outsideDimensions;
    }

    public void setOutsideDimensions(int outsideDimensions) {
        this.outsideDimensions = outsideDimensions;
    }

    public int getPermittedTowWeight() {
        return permittedTowWeight;
    }

    public void setPermittedTowWeight(int permittedTowWeight) {
        this.permittedTowWeight = permittedTowWeight;
    }
}
