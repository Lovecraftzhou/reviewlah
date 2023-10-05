package com.reviewlah.db.vo;

import java.math.BigInteger;

public class Address {
    private int address_id;
    private String address_code;
    private BigInteger merchant_id;
    private String address_email;
    private String unitnum;
    public  Address() {

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

    public String getAddress_email() {
        return address_email;
    }

    public void setAddress_email(String address_email) {
        this.address_email = address_email;
    }

    public String getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }



}
