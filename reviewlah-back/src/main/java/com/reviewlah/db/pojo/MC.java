package com.reviewlah.db.pojo;

import java.math.BigInteger;

public class MC {
    private BigInteger id;
    private int category_id;
    private BigInteger merchant_id;
    public MC() {

    }
    public MC(int category_id, BigInteger merchant_id) {
        this.category_id = category_id;
        this.merchant_id = merchant_id;
    }
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public BigInteger getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(BigInteger merchant_id) {
        this.merchant_id = merchant_id;
    }


}
