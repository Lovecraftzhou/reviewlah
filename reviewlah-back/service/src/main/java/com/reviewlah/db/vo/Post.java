package com.reviewlah.db.vo;

import java.math.BigInteger;
import java.util.Date;

public class Post {
    private BigInteger posts_id;
    private BigInteger customer_id;
    private String content;
    private String pic_post;
    private Date time_post;
    public Post() {

    }
    public BigInteger getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(BigInteger posts_id) {
        this.posts_id = posts_id;
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


}
