package com.reviewlah.controller.form;

import java.math.BigInteger;

public class InsertBrowseHistoryRequest {
    private int category_id;
    public int getCategory_id(){
        return category_id;
    }
    public void setCategory_id(int category_id){
        this.category_id=category_id;
    }

    private BigInteger user_id;
    public BigInteger getUser_id() {
        return user_id;
    }
    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }
}
