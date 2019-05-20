package com.etollpay.srpc.standard.etcchina.issuer;


import com.etollpay.srpc.standard.basic.ICsvBean;

/**
 * Created by zhangxuhua on 2018/11/6.
 */
public class IssuerOrderDeliveryIndexInfo implements ICsvBean {

    private String orderNo;
    private long orderTime;

    private String consigneeProvince;
    private String consigneeCity;
    private String consigneeArea;
    private String districtCode;
    private String consigneeAddress;

    private String consigneeName;
    private String consigneeTel;
    private int customerType;
    private String customerFile;
    private String vehicleFile;


    private String bankId;
    private String accountName;
    private String tel;
    private String accountNo;
    private String contractId;


    private boolean free;
    private long obuPrice;
    private long allowance;
    private boolean needInvoice;
    private String invoiceTitle;

    private String taxNo;
    private String companyAddress;
    private String companyTel;
    private String companyBank;
    private String companyBankAccount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince;
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeArea() {
        return consigneeArea;
    }

    public void setConsigneeArea(String consigneeArea) {
        this.consigneeArea = consigneeArea;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    public String getCustomerFile() {
        return customerFile;
    }

    public void setCustomerFile(String customerFile) {
        this.customerFile = customerFile;
    }

    public String getVehicleFile() {
        return vehicleFile;
    }

    public void setVehicleFile(String vehicleFile) {
        this.vehicleFile = vehicleFile;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String[] getFields() {
        return new String[] {
                "orderNo",
                "orderTime",

                "consigneeProvince",
                "consigneeCity",
                "consigneeArea",
                "districtCode",
                "consigneeAddress",

                "consigneeName",
                "consigneeTel",
                "customerType",
                "customerFile",
                "vehicleFile",

                "bankId",
                "accountName",
                "tel",
                "accountNo",
                "contractId",

                "free",
                "obuPrice",
                "allowance",
                "needInvoice",
                "invoiceTitle",

                "taxNo",
                "companyAddress",
                "companyTel",
                "companyBank",
                "companyBankAccount"
        };
    }
}
