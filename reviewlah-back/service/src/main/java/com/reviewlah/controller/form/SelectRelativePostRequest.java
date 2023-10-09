package com.reviewlah.controller.form;

import java.math.BigInteger;

public class SelectRelativePostRequest {
    private String keyword;
    private BigInteger user_id;
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }


}
