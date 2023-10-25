package com.reviewlah.controller.form;

import java.math.BigInteger;

public class SelectBrowseHistoryByCustomerIdRequest {
    private BigInteger history_id;
    public BigInteger getHistory_id(){
        return history_id;
    }
    public void setHistory_id(BigInteger history_id){
        this.history_id=history_id;
    }

    private BigInteger user_id;
    public BigInteger getUser_id() {
        return user_id;
    }
    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }
}
