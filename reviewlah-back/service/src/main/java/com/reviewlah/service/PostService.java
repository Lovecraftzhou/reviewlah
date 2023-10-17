package com.reviewlah.service;

import com.reviewlah.db.pojo.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public interface PostService {
    void insertPost(Post post);
    void deletePostByPostId(BigInteger post_id);
    ArrayList<HashMap> selectPostByCustomerId(BigInteger customer_id);
    Post selectPostByPostId(BigInteger post_id);
    ArrayList<HashMap> selectAllPostExceptMine(BigInteger customer_id);
    ArrayList<HashMap> selectRelativePost(@Param("keyword") String keyword, @Param("customer_id") BigInteger customer_id);
}
