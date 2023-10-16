package com.reviewlah.controller.form;

import java.math.BigInteger;
import java.util.Date;

public class SelectTop3CategoryFromBrowseHistoryRequest {
    private BigInteger user_id;
    private Date time_his;
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

    public Date getTime() {return time_his;
    }
    public void setTime_his(Date time_his){this.time_his=time_his;}
}
