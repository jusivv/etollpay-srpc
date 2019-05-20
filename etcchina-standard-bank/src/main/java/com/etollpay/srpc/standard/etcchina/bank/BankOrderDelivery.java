package com.etollpay.srpc.standard.etcchina.bank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangxuhua on 2018/9/20.
 *
 * 银行（合作方）订单同步数据
 */
public class BankOrderDelivery {

    /**
     * 事件编号（订单编号），合作方生成的唯一编号
     */
    @Size(max = 32)
    private String orderId;

    /**
     *发行系统生成的订单编号
     */
    @NotNull
    @Size(max = 32)
    private String etcOrderId;
    /**
     *合作方编号
     */
    @NotNull
    @Size(max = 32)
    private String bankId;
    /**
     *发行方编号
     */
    @NotNull
    @Size(max = 32)
    private String issuerId;
    /**
     *车主身份证姓名
     */
    @NotNull
    @Size(max = 30)
    private String idName;
    /**
     *车主身份证号码
     */
    @NotNull
    @Size(max = 20)
    private String idNo;
    /**
     *车辆行驶证号牌号码
     */
    @NotNull
    @Size(max = 15)
    private String vehNo;
    /**
     *车辆行驶证上的车辆识别代号
     */
    @NotNull
    @Size(max = 30)
    private String vin;
    /**
     *银行账户开户名
     */
    @NotNull
    @Size(max = 30)
    private String accountName;
    /**
     *银行账号
     */
    @Size(max = 50)
    private String accountNo;
    /**
     *代扣协议编号
     */
    @NotNull
    @Size(max = 32)
    private String contractId;
    /**
     *银行预留手机
     */
    @NotNull
    @Size(max = 20)
    private String tel;
    /**
     *收件人姓名
     */
    @Size(max = 30)
    private String recipientName;
    /**
     *收件人手机
     */
    @Size(max = 20)
    private String recipientTel;
    /**
     *收件地6位行政区划代码
     */
    @Size(max = 6)
    private String recipientDistrictCode;
    /**
     *收件地址
     */
    @Size(max = 100)
    private String recipientAddress;

    /**
     *  是否免除标签费用
     */
    private boolean free;

    /**
     *  标签价格，单位：分
     */
    @Min(0)
    @Max(Long.MAX_VALUE)
    private long obuPrice;

    /**
     *  合作方补贴金额，单位：分
     */
    @Min(0)
    @Max(Long.MAX_VALUE)
    private long allowance;


    /**
     *  是否需要开具发票，免除标签费用的此字段为false
     */
    private boolean needInvoice;


    /**
     *发票抬头
     */
    @Size(max = 100)
    private String invoiceTitle;


    /**
     *税号
     */
    @Size(max = 50)
    private String taxNo;


    /**
     *单位地址
     */
    @Size(max = 120)
    private String companyAddress;



    /**
     *电话号码
     */
    @Size(max = 20)
    private String companyTel;


    /**
     *开户银行
     */
    @Size(max = 90)
    private String companyBank;


    /**
     *银行账户
     */
    @Size(max = 50)
    private String companyBankAccount;





    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEtcOrderId() {
        return etcOrderId;
    }

    public void setEtcOrderId(String etcOrderId) {
        this.etcOrderId = etcOrderId;
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

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getVehNo() {
        return vehNo;
    }

    public void setVehNo(String vehNo) {
        this.vehNo = vehNo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientTel() {
        return recipientTel;
    }

    public void setRecipientTel(String recipientTel) {
        this.recipientTel = recipientTel;
    }

    public String getRecipientDistrictCode() {
        return recipientDistrictCode;
    }

    public void setRecipientDistrictCode(String recipientDistrictCode) {
        this.recipientDistrictCode = recipientDistrictCode;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }


    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public long getObuPrice() {
        return obuPrice;
    }

    public void setObuPrice(long obuPrice) {
        this.obuPrice = obuPrice;
    }

    public long getAllowance() {
        return allowance;
    }

    public void setAllowance(long allowance) {
        this.allowance = allowance;
    }

    public boolean isNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(boolean needInvoice) {
        this.needInvoice = needInvoice;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyBank() {
        return companyBank;
    }

    public void setCompanyBank(String companyBank) {
        this.companyBank = companyBank;
    }

    public String getCompanyBankAccount() {
        return companyBankAccount;
    }

    public void setCompanyBankAccount(String companyBankAccount) {
        this.companyBankAccount = companyBankAccount;
    }
}
