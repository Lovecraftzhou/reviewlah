package com.reviewlah.controller.form;

import java.math.BigInteger;

public class InsertPostCommentRequest {
    private BigInteger customer_id;
    private String content;

    public BigInteger getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(BigInteger customer_id) {
        this.customer_id = customer_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
