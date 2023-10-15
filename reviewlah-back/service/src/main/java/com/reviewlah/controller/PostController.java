package com.reviewlah.controller;

import com.reviewlah.controller.form.*;
import com.reviewlah.db.pojo.Post;
import com.reviewlah.db.pojo.User;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.PostService;
import com.reviewlah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping({"/post"})
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PostService postService;
    @PostMapping({"/insert"})
    public void insertPost(@RequestBody InsertPostRequest request) {
        BigInteger user_id = request.getUser_id();
        String title = request.getTitle();
        String content = request.getContent();
        String pic_post = request.getPic_post();
//        if(pic_post == null || pic_post == "") pic_post = "";
        Date date = new Date();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            if(content == null || content.isEmpty()) {
                System.out.println("Content Cannot Be Empty");
                return;
            }
            if(title == null || title.isEmpty()) {
                System.out.println("Title Cannot Be Empty");
                return;
            }
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            Post post = new Post();
            post.setCustomer_id(customer_id);
            post.setTitle(title);
            post.setContent(content);
            post.setPic_post(pic_post);
            post.setTime_post(date);
            this.postService.insertPost(post);
            System.out.println("successful");
        }
        else {
            System.out.println("failed");
        }

    }
    @PostMapping({"/delete"})
    public void deletePost(@RequestBody DeletePostRequest request) {
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post != null) {
            this.postService.deletePostByPostId(post_id);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
        }
    }
    @PostMapping({"/mypost"})
    public ArrayList<Post> selectPostByCustomerId(@RequestBody SelectPostByCustomerIdRequest request) {
        BigInteger user_Id = request.getUser_id();
        User user = this.userService.selectUserById(user_Id);
        if(user != null) {
            ArrayList<Post> list = new ArrayList<>();
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_Id);
            list = this.postService.selectPostByCustomerId(customer_id);
            System.out.println("successful");
            return list;
        }
        else {
            System.out.println("User Does Not Exist");
        }
        return null;
    }
    @PostMapping({"/homepage"})
    public ArrayList<Post> selectAllPostExceptMine(@RequestBody SelectPostByCustomerIdRequest request) {
        BigInteger user_id = request.getUser_id();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            ArrayList<Post> list = new ArrayList<>();
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            list = this.postService.selectAllPostExceptMine(customer_id);
            System.out.println("successful");
            return list;
        }
        else {
            System.out.println("User Does Not Exist");
        }
        return null;
    }
    @PostMapping({"/homepage/relative"})
    public ArrayList<Post> selectRelativePost(@RequestBody SelectRelativePostRequest request) {
        BigInteger user_id = request.getUser_id();
        String keyword = request.getKeyword();
        User user = this.userService.selectUserById(user_id);
        if(user != null) {
            ArrayList<Post> list = new ArrayList<>();
            BigInteger customer_id = this.customerService.selectCustomerIdByUserId(user_id);
            list = this.postService.selectRelativePost(keyword, customer_id);
            System.out.println("successful");
            return list;
        }
        else {
            System.out.println("User Does Not Exist");
        }
        return null;
    }
    @PostMapping({"/detail"})
    public Post selectPostByPostId(@RequestBody SelectPostByPostIdRequest request) {
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post == null) {
            System.out.println("Post Does Not Exist");
        }
        else {
            System.out.println("successful");
        }
        return post;
    }
}
