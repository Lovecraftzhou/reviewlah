package com.reviewlah.controller.form;

import java.math.BigInteger;

public class DeleteBrowseHistoryByHistoryIDRequest {
    private BigInteger history_id;
    public BigInteger getHistory_id(){
        return history_id;
    }
    public void setHistory_id(BigInteger history_id){
        this.history_id=history_id;
    }
}
