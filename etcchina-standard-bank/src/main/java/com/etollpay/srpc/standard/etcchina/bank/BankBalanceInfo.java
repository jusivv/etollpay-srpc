package com.etollpay.srpc.standard.etcchina.bank;

import com.etollpay.srpc.standard.basic.ICsvBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangxuhua on 2018/9/20.
 */
public class BankBalanceInfo implements ICsvBean {

    @NotNull
    @Size(max = 50)
    private String  serialNo;
    @NotNull
    @Size(max = 32)
    private String  bankId;
    @NotNull
    @Size(max = 32)
    private String  issuerId;
    @NotNull
    @Size(max = 50)
    private String  userId;
    @NotNull
    @Size(max = 32)
    private String  contractId;
    @NotNull
    @Size(max = 20)
    private String  etcCardNo;
    @Min(1)
    @Max(2)
    private int  type;
    @NotNull
    @Size(max = 20)
    private String  tradeTime;
    @Min(0)
    @Max(Long.MAX_VALUE)
    private long  amount;
    @Min(0)
    @Max(Long.MAX_VALUE)
    private long  fee;

    @NotNull
    @Size(max = 10)
    private String  rate;
    @NotNull
    @Size(max = 20)
    private String tallyDate;
    @NotNull
    @Size(max = 20)
    private String paymentDate;
    private boolean success;
    @Size(max = 200)
    private String remark;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getEtcCardNo() {
        return etcCardNo;
    }

    public void setEtcCardNo(String etcCardNo) {
        this.etcCardNo = etcCardNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTallyDate() {
        return tallyDate;
    }

    public void setTallyDate(String tallyDate) {
        this.tallyDate = tallyDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String[] getFields() {
        return new String[]{
                "serialNo",
                "bankId",
                "userId",
                "contractId",
                "issuerId",
                "etcCardNo",
                "type",
                "tradeTime",
                "amount",
                "fee",
                "rate",
                "tallyDate",
                "paymentDate",
                "success",
                "remark"
        };
    }
}
