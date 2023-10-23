package com.reviewlah.db.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

public class BrowseHistory {
    private BigInteger history_id;
    private BigInteger customer_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time_his;
    private int category_id;
    public BrowseHistory(){

    }
    public BigInteger getHistory_id() {
        return history_id;
    }

    public void setHistory_id(BigInteger history_id) {
        this.history_id = history_id;
    }

    public BigInteger getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(BigInteger customer_id) {
        this.customer_id = customer_id;
    }

    public Date getTime_his() {
        return time_his;
    }

    public void setTime_his(Date time_his) {
        this.time_his = time_his;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
