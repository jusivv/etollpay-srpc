package com.etollpay.srpc.standard.etcchina.issuer;

import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * 卡签状态变更信息
 */
public class IssuerEtcStatusModifyInfo implements ICsvBean {
     /**
      * 客户编号，17位数字，参见编码规则
      */
     private String userId;
     /**
      * 卡片网络编号
      */
     private String cpuNetId;
     /**
      * CPU卡内部编号
      */
     private String cpuNum;
     /**
      * OBU网络编号
      */
     private String obuNetId;
     /**
      * OBU内部编号
      */
     private String obuNum;
     /**
      * 车辆编号，“<车牌号码>_<车牌颜色编号>”，如“京A12345_1”
      */
     private String vehicleId;
     /**
      * 业务类型：1、进入黑名单  2、移除黑名单   3、注销   4、挂失  5、解挂
      */
     private int bizType;
     /**
      * 业务范围：1、卡片；2、OBU；3、卡片+OBU
      */
     private int bizScope;
     /**
      * 业务办理时间，YYYY-MM-DDTHH:mm:ss
      */
     private String bizTime;
     /**
      * 办理渠道：1、线上；2、线下
      */
     private int applyChannel;
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

     public int getBizType() {
         return bizType;
     }

     public void setBizType(int bizType) {
         this.bizType = bizType;
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

     public int getApplyChannel() {
         return applyChannel;
     }

     public void setApplyChannel(int applyChannel) {
         this.applyChannel = applyChannel;
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
                 "cpuNetId",
                 "cpuNum",
                 "obuNetId",
                 "obuNum",
                 "vehicleId",
                 "bizType",
                 "bizScope",
                 "bizTime",
                 "applyChannel",
                 "remark"
         };
     }
 }

