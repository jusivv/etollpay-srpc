package com.etollpay.srpc.standard.etcchina.issuer;

/**
 * Created by zhangxuhua on 2018/11/6.
 */
public class IssuerOrderDeliveryUserInfo {
    private String userId;
    private String userName;
    private int userIdType;
    private String userIdNum;
    private String registeredTime;
    private String tel;
    private String address;
    private String userIdFileFront;
    private String userIdFileBack;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(int userIdType) {
        this.userIdType = userIdType;
    }

    public String getUserIdNum() {
        return userIdNum;
    }

    public void setUserIdNum(String userIdNum) {
        this.userIdNum = userIdNum;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserIdFileFront() {
        return userIdFileFront;
    }

    public void setUserIdFileFront(String userIdFileFront) {
        this.userIdFileFront = userIdFileFront;
    }

    public String getUserIdFileBack() {
        return userIdFileBack;
    }

    public void setUserIdFileBack(String userIdFileBack) {
        this.userIdFileBack = userIdFileBack;
    }
}
