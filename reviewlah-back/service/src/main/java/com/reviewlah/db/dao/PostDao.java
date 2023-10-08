package com.reviewlah.db.dao;

import com.reviewlah.db.pojo.Post;

import java.math.BigInteger;
import java.util.ArrayList;

public interface PostDao {
    void insertPost(Post post);
    void deletePostByPostId(BigInteger post_id);
    ArrayList<Post> selectPostByCustomerId(BigInteger customer_id);
    Post selectPostByPostId(BigInteger post_id);
}
