package com.etollpay.srpc.standard.basic;

public enum IntfError {
    FILE_NOT_FOUND(10001, "未找到业务数据文件")
    ,ILLEGAL_FILE_HASH(10002, "文件哈希校验失败")
    ,UNSUPPORT_ENCRYPTION(10003, "不支持的加密方式")
    ,BIZPROCESSOR_NOT_FOUND(10004, "未找到业务处理器")
    ,ILLEGAL_REQTYP(10005, "无效的接口请求类型")
    ,DUPLICATE_BIZ_FILE(10006, "重复的业务数据文件")
    ,LOST_BIZ_FILE(10007, "业务数据文件丢失")
    ,DATA_NOT_CONFORM_STANDARD(10008, "业务数据文件数据不符合规范")
    ,ILLEGAL_MAC(10009, "元数据校验码验证失败")
    ,PRIVILEGE_AUTHENTICATION_FAILED(10010, "无接口访问权限")
    ,NOT_FOUND_SYSCONFIGKEY(10011, "无法找到匹配的系统参数")




    ,INTERNAL_ERROR(19998, "系统内部错误")
    ,UNKNOWN_ERROR(19999, "未定义的错误")


    ,ORDER_NOT_FOUND(20001, "找不到订单数据")
    ,VEHICLE_HAS_ETC(20002, "车辆已办理ETC")
    ,NOT_FOUND_ETCCARDNO(20003, "未找到对应的ETC卡")
    ,NOT_FOUND_USERID(20004, "未找到对应的发行系统用户")
    ,HAS_TRADE_SERIALNO(20005, "已存在该交易流水")
    ,INVALID_CONTRACTID(20006, "无效的代扣协议编号")
    ,APPLY_DETAIL_NOSUCCRSS(20007, "申请资料信息错误")
    ,DUPLICATE_ETC_CARD_NO(20008, "重复的ETC卡号")
    ,DUPLICATE_ETC_OBU_NO(20008, "重复的OBU卡号")
    ;


    private int code;
    private String message;

    IntfError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
