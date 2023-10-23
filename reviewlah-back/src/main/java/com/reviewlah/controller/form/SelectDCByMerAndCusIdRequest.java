package com.reviewlah.controller.form;

import java.math.BigInteger;

public class SelectDCByMerAndCusIdRequest {
    private BigInteger customer_user_id;
    private BigInteger merchant_user_id;
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

}
