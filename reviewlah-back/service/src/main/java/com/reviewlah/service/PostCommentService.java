package com.reviewlah.service;

import com.reviewlah.db.pojo.PostComment;

import java.math.BigInteger;
import java.util.ArrayList;

public interface PostCommentService {
    ArrayList<PostComment> selectPostCommentByPostId(BigInteger post_id);
    PostComment selectPostCommentByPCId(BigInteger post_com_id);
    void insertPostComment(PostComment postComment);
    void deletePostCommentByPCId(BigInteger post_com_id);
}
