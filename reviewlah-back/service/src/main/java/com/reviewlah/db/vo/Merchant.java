package com.reviewlah.db.vo;

import java.math.BigInteger;

public class Merchant extends User{
    private BigInteger merchant_id;
    private BigInteger user_id;
    public Merchant() {
    }
    public BigInteger getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(BigInteger merchant_id) {
        this.merchant_id = merchant_id;
    }

    @Override
    public BigInteger getUser_id() {
        return user_id;
    }

    @Override
    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

}
