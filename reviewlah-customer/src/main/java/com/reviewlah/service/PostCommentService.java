package com.reviewlah.service;

import com.reviewlah.db.pojo.PostComment;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public interface PostCommentService {
    ArrayList<PostComment> selectPostCommentByPostId(BigInteger post_id);
    PostComment selectPostCommentByPCId(BigInteger post_com_id);
    void insertPostComment(PostComment postComment);
    void deletePostCommentByPCId(BigInteger post_com_id);
    ArrayList<HashMap> selectPostMapCommentByPostId(BigInteger post_id);
    ArrayList<HashMap> selectPostMapCommentByCusAndPostId(@Param("post_id") BigInteger post_id, @Param("customer_id") BigInteger customer_id);
    void updatePostCommentByPCId(PostComment postComment);
}
