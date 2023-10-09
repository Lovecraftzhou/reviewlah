package com.reviewlah.controller.form;

import java.math.BigInteger;

public class DeletePostCommentByPCIdRequest {
    private BigInteger post_com_id;
    public BigInteger getPost_com_id() {
        return post_com_id;
    }

    public void setPost_com_id(BigInteger post_com_id) {
        this.post_com_id = post_com_id;
    }


}
