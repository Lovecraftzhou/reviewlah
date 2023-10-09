package com.reviewlah.service.impl;

import com.reviewlah.db.dao.PostDao;
import com.reviewlah.db.pojo.Post;
import com.reviewlah.service.PostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;
    public void insertPost(Post post) {
        this.postDao.insertPost(post);
    }
    public void deletePostByPostId(BigInteger post_id) {
        this.postDao.deletePostByPostId(post_id);
    }
    public ArrayList<Post> selectPostByCustomerId(BigInteger customer_id) {
        ArrayList<Post> list = this.postDao.selectPostByCustomerId(customer_id);
        return list;
    }
    public Post selectPostByPostId(BigInteger post_id) {
        Post post = this.postDao.selectPostByPostId(post_id);
        return post;
    }
    public ArrayList<Post> selectAllPostExceptMine(BigInteger customer_id) {
        ArrayList<Post> list = this.postDao.selectAllPostExceptMine(customer_id);
        return list;
    }
    public ArrayList<Post> selectRelativePost(@Param("keyword") String keyword, @Param("customer_id") BigInteger customer_id) {
        ArrayList<Post> list = this.postDao.selectRelativePost(keyword, customer_id);
        return list;
    }
}
