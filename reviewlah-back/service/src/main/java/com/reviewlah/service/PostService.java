package com.reviewlah.service;

import com.reviewlah.db.pojo.Post;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
@Service
public interface PostService {
    void insertPost(Post post);
    void deletePostByPostId(BigInteger post_id);
    ArrayList<Post> selectPostByCustomerId(BigInteger customer_id);
    Post selectPostByPostId(BigInteger post_id);
}
