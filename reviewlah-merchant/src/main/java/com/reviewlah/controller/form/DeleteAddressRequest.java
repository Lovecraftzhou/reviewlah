package com.reviewlah.controller.form;

import java.math.BigInteger;

public class DeleteAddressRequest {
    public BigInteger getAddress_id() {
        return address_id;
    }

    public void setAddress_id(BigInteger address_id) {
        this.address_id = address_id;
    }

    private BigInteger address_id;
}
