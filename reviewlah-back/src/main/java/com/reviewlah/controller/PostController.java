package com.reviewlah.controller;

import com.reviewlah.controller.form.DeletePostRequest;
import com.reviewlah.controller.form.InsertPostRequest;
import com.reviewlah.controller.form.SelectPostByCustomerIdRequest;
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
}
