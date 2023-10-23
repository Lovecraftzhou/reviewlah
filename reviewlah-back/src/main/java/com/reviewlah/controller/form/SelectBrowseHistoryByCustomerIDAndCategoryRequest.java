package com.reviewlah.controller.form;

import java.math.BigInteger;

public class SelectBrowseHistoryByCustomerIDAndCategoryRequest {
    private BigInteger user_id;
    public BigInteger getUser_id() {
        return user_id;
    }
    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }
    private String category_name;
    public String getCatgoryName(){
        return category_name;
    }
}
