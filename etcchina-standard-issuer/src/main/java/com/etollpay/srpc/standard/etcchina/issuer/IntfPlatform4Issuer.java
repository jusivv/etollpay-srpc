package com.etollpay.srpc.standard.etcchina.issuer;

public enum IntfPlatform4Issuer {
     dryRun              // 测试接口
    ,issueResult        // 发行结果通知接口
    ,obuRegister        // 标签激活结果通知接口
    ,etcStatusModify    // 卡签状态变更通知接口
    ,etcReplace         // 卡签更换通知接口
    ,tollBill           // 实时账单同步接口
    ,tollBillDaily      // 日结账单同步接口
    ,tollRefund         // 退款接口
    ,etcUnregister    // 卡片注销通知接口


}
