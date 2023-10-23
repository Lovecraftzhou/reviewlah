package com.reviewlah.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.reviewlah.db.pojo.Post;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService service;

    @Test
    void test() {
        Post post1 = new Post();
        post1.setCustomer_id(BigInteger.ONE);
        post1.setTitle("post 1");
        post1.setContent("post content");
        post1.setTime_post(new Date());
        post1.setPic_post("https://www.googole.com");
        service.insertPost(post1);
        post1 = service.selectPostByPostId(post1.getPost_id());
        assertNotNull(post1);
        ArrayList<HashMap> maps = service.selectPostByCustomerId(BigInteger.ONE);
        assertFalse(maps.isEmpty());
        Post post2 = new Post();
        post2.setCustomer_id(BigInteger.ONE);
        post2.setTitle("post 2");
        post2.setContent("post content 2");
        post2.setTime_post(new Date());
        post2.setPic_post("https://www.googole.com");
        service.insertPost(post2);
        maps = service.selectAllPostExceptMine(BigInteger.ONE);
        assertFalse(maps.isEmpty());
        maps = service.selectRelativePost("post", BigInteger.ONE);
        assertFalse(maps.isEmpty());
        service.deletePostByPostId(post1.getPost_id());
        service.deletePostByPostId(post2.getPost_id());
    }
}
