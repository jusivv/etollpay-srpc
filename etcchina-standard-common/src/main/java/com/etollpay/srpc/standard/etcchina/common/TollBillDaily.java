package com.etollpay.srpc.standard.etcchina.common;


import com.etollpay.srpc.standard.basic.ICsvBean;

public class TollBillDaily extends AbstractTollBill implements ICsvBean {
    /**
     * 账单日期，YYYY-MM-DD
     */
    private String billDate;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String[] getFields() {
        return new String[] {
                "partnerId",
                "issueType",
                "bankId",
                "collectionAccount",
                "payAccount",
                "contractId",
                "billNo",
                "billAmount",
                "userId",
                "cardNo",
                "vehicleId",
                "billDate"
        };
    }
}
