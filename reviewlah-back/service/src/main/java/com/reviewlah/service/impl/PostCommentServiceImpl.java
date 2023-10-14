package com.reviewlah.service.impl;

import com.reviewlah.db.dao.PostCommentDao;
import com.reviewlah.db.pojo.PostComment;
import com.reviewlah.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Autowired
    private PostCommentDao postCommentDao;
    public ArrayList<PostComment> selectPostCommentByPostId(BigInteger post_id) {
        return this.postCommentDao.selectPostCommentByPostId(post_id);
    }
    public PostComment selectPostCommentByPCId(BigInteger post_com_id) {
        return this.postCommentDao.selectPostCommentByPCId(post_com_id);
    }
    public void insertPostComment(PostComment postComment) {
        this.postCommentDao.insertPostComment(postComment);
    }
    public void deletePostCommentByPCId(BigInteger post_com_id) {
        this.postCommentDao.deletePostCommentByPCId(post_com_id);
    }
}
