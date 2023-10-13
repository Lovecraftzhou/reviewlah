package com.reviewlah.controller;

import com.reviewlah.controller.form.DeletePostCommentByPCIdRequest;
import com.reviewlah.controller.form.InsertPostCommentRequest;
import com.reviewlah.controller.form.SelectPostCommentByPostIdRequest;
import com.reviewlah.db.pojo.MC;
import com.reviewlah.db.pojo.Post;
import com.reviewlah.db.pojo.PostComment;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.PostCommentService;
import com.reviewlah.service.PostService;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping({"/post/detail"})
public class PostCommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostCommentService postCommentService;
    @PostMapping({"/post_comment"})
    public ArrayList<Map<String, Object>> selectPostCommentByPostId(@RequestBody SelectPostCommentByPostIdRequest request) {
        ArrayList<Map<String, Object>> res = new ArrayList<>();
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post != null) {
            ArrayList<PostComment> list = this.postCommentService.selectPostCommentByPostId(post_id);
            for(PostComment tmp : list) {
                BigInteger customer_id = tmp.getCustomer_id();
                BigInteger user_id = this.customerService.selectUserIdByCustomerId(customer_id);
                User user = this.userService.selectUserById(user_id);
                if(user != null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", user.getName());
                    map.put("avator", user.getAvator());
                    map.put("content", tmp.getContent());
                    map.put("time_pc", tmp.getTime_pc());
                    res.add(map);
                }
                else {
                    System.out.println("User Does Not Exist");
                }
            }

        }
        else {
            System.out.println("Post Does Not Exist");
            return null;
        }
        return res;
    }
    @PostMapping({"/post_comment/insert"})
    public void insertPostComment(@RequestBody InsertPostCommentRequest request) {
        BigInteger user_id = request.getUser_id();
        BigInteger post_id = request.getPost_id();
        String content = request.getContent();
        User user = this.userService.selectUserById(user_id);
        Date date = new Date();
        if(user != null) {
            if(content == null || content.isEmpty()) {
                System.out.println("Content Cannot Be Empty");
                return;
            }
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            PostComment postComment = new PostComment();
            postComment.setCustomer_id(customer_id);
            postComment.setPost_id(post_id);
            postComment.setContent(content);
            postComment.setTime_pc(date);
            this.postCommentService.insertPostComment(postComment);
            System.out.println("successful");
        }
        else {
            System.out.println("failed");
        }
    }
    @PostMapping({"/post_comment/delete"})
    public void deletePostCommentByPCId(@RequestBody DeletePostCommentByPCIdRequest request) {
        BigInteger post_com_id = request.getPost_com_id();
        PostComment postComment = this.postCommentService.selectPostCommentByPCId(post_com_id);
        if(postComment != null) {
            this.postCommentService.deletePostCommentByPCId(post_com_id);
            System.out.println("successful");
        }
        else {
            System.out.println("PostComment Does Not Exist");
        }
    }
}
