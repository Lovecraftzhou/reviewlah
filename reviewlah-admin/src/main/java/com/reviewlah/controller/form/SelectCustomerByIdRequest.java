package com.reviewlah.controller.form;

import java.math.BigInteger;

public class SelectCustomerByIdRequest {
    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    private BigInteger user_id;
}
