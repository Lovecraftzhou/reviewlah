package com.reviewlah.controller.form;

import java.math.BigInteger;
import java.util.Date;

public class InsertPostRequest {
    private BigInteger user_id;
    private String title;
    private String content;
    private String pic_post;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic_post() {
        return pic_post;
    }

    public void setPic_post(String pic_post) {
        this.pic_post = pic_post;
    }

}
