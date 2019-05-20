package com.etollpay.srpc.standard.etcchina.issuer;

public enum IntfIssuer {
    orderDelivery,   // 订单同步接口
    tollChecking,    // 对账接口
    blackListReq,    // 黑名单申请接口
    vehicleVerify,   //车辆信息验证接口

    expressTrace   //快递跟踪信息
}
