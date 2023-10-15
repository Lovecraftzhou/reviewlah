package com.reviewlah.db.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

public class Post {
    private BigInteger post_id;
    private BigInteger customer_id;
    private String title;
    private String content;
    private String pic_post;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time_post;
    public Post() {

    }
    public BigInteger getPosts_id() {
        return post_id;
    }

    public void setPosts_id(BigInteger post_id) {
        this.post_id = post_id;
    }

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

    public String getPic_post() {
        return pic_post;
    }

    public void setPic_post(String pic_post) {
        this.pic_post = pic_post;
    }

    public Date getTime_post() {
        return time_post;
    }

    public void setTime_post(Date time_post) {
        this.time_post = time_post;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
