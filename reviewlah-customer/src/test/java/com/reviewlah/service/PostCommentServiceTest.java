package com.reviewlah.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.PostComment;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostCommentServiceTest {
    @Autowired
    private PostCommentService service;

    @Test
    void test() {
        PostComment postComment = new PostComment();
        postComment.setPost_id(BigInteger.ONE);
        postComment.setCustomer_id(BigInteger.ONE);
        postComment.setContent("test comment");
        postComment.setTime_pc(new Date());
        service.insertPostComment(postComment);
        postComment = service.selectPostCommentByPCId(postComment.getPost_com_id());
        assertNotNull(postComment);
        ArrayList<PostComment> comments = service.selectPostCommentByPostId(BigInteger.ONE);
        assertFalse(comments.isEmpty());
        ArrayList<HashMap> maps = service.selectPostMapCommentByPostId(BigInteger.ONE);
        assertFalse(maps.isEmpty());
        maps = service.selectPostMapCommentByCusAndPostId(BigInteger.ONE, BigInteger.ONE);
        assertFalse(maps.isEmpty());
        service.deletePostCommentByPCId(postComment.getPost_com_id());
    }
}
