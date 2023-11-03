package com.reviewlah.db.pojo;

import java.math.BigInteger;
import java.util.ArrayList;

public class Merchant extends User{
    private BigInteger merchant_id;
    private BigInteger user_id;
    private double avg_rate;
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
    public double getAvg_rate() {
        return avg_rate;
    }

    public void setAvg_rate(double avg_rate) {
        this.avg_rate = avg_rate;
    }

}
