package com.reviewlah.controller.form;

import java.math.BigInteger;

public class InsertPostCommentRequest {
    private BigInteger user_id;
    private BigInteger post_id;
    private String content;

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
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


}
