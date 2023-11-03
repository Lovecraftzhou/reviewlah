package com.reviewlah.controller.form;

import javax.xml.crypto.Data;
import java.math.BigInteger;

public class InsertAnnouncementRequest {
    private BigInteger user_id;
    private String content;


    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public String getContent(){ return content;}

    public void setContent(){this.content=content;}
}
