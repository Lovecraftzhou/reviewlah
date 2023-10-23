package com.reviewlah.controller.form;

import java.math.BigInteger;

public class InsertDiningCommentRequest {
    private BigInteger customer_user_id;
    private BigInteger merchant_user_id;
    private String content;
    private int rate;
    private String pic_dc;
    public BigInteger getCustomer_user_id() {
        return customer_user_id;
    }

    public void setCustomer_user_id(BigInteger customer_user_id) {
        this.customer_user_id = customer_user_id;
    }

    public BigInteger getMerchant_user_id() {
        return merchant_user_id;
    }

    public void setMerchant_user_id(BigInteger merchant_user_id) {
        this.merchant_user_id = merchant_user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getPic_dc() {
        return pic_dc;
    }

    public void setPic_dc(String pic_dc) {
        this.pic_dc = pic_dc;
    }

}
