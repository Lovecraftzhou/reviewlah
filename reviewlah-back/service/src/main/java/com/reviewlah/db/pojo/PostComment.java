package com.reviewlah.db.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

public class PostComment {
    private BigInteger post_com_id;
    private BigInteger customer_id;
    private BigInteger post_id;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time_pc;
    public PostComment() {

    }
    public BigInteger getPost_com_id() {
        return post_com_id;
    }

    public void setPost_com_id(BigInteger post_com_id) {
        this.post_com_id = post_com_id;
    }

    public BigInteger getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(BigInteger customer_id) {
        this.customer_id = customer_id;
    }

    public BigInteger getPost_id() {
        return post_id;
    }

    public void setPost_id(BigInteger post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime_pc() {
        return time_pc;
    }

    public void setTime_pc(Date time_pc) {
        this.time_pc = time_pc;
    }


}
