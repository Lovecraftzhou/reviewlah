package com.reviewlah.controller.form;

import java.math.BigInteger;

public class SelectPostCommentByPostIdRequest {

    private BigInteger post_id;

    public BigInteger getPost_id() {
        return post_id;
    }

    public void setPost_id(BigInteger post_id) {
        this.post_id = post_id;
    }
}
