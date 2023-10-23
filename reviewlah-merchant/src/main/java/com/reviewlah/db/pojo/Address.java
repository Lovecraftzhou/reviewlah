package com.reviewlah.db.pojo;

import java.math.BigInteger;

public class Address {
    private int address_id;
    private String address_code;
    private BigInteger merchant_id;
    private String address_detail;
    private String unitnum;
    public  Address() {

    }
    public Address(String address_code, BigInteger merchant_id, String address_detail, String unitnum) {
        this.address_code = address_code;
        this.merchant_id = merchant_id;
        this.address_detail = address_detail;
        this.unitnum = unitnum;
    }
    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress_code() {
        return address_code;
    }

    public void setAddress_code(String address_code) {
        this.address_code = address_code;
    }

    public BigInteger getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(BigInteger merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }



}
