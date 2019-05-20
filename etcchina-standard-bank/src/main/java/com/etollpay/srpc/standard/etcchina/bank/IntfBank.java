package com.etollpay.srpc.standard.etcchina.bank;

public enum IntfBank {
    cancelOrder,     // 订单取消通知接口
    issueResult,     // 发行结果通知接口
    send,             // 订单发货通知接口（可选）
    obuActived,      // 电子标签激活通知接口（可选）
    etcUnregister,   // 卡片注销通知接口
    etcReplace,      // 卡签更换通知接口
    payment,         // 代扣接口
    refund,          // 退款接口（待定）
}
