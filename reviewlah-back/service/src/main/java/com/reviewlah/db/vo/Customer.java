package com.reviewlah.db.vo;

import java.math.BigInteger;

public class Customer extends User{

    private BigInteger customer_id;
    private BigInteger user_id;
    public Customer() {
    }
    public BigInteger getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(BigInteger customer_id) {
        this.customer_id = customer_id;
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
