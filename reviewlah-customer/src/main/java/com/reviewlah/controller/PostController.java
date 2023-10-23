package com.reviewlah.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeletePostRequest;
import com.reviewlah.controller.form.InsertPostRequest;
import com.reviewlah.controller.form.SelectPostByCustomerIdRequest;
import com.reviewlah.controller.form.SelectPostByPostIdRequest;
import com.reviewlah.controller.form.SelectRelativePostRequest;
import com.reviewlah.db.pojo.Customer;
import com.reviewlah.db.pojo.Post;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.PostService;

@RestController
@RequestMapping({"/customer/post"})
public class PostController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PostService postService;
    @PostMapping({"/insert"})
    public RCode insertPost(@RequestBody InsertPostRequest request) {
        BigInteger user_id = request.getUser_id();
        String title = request.getTitle();
        String content = request.getContent();
        String pic_post = request.getPic_post();
        Date date = new Date();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        if(customer != null) {
            if(content == null || content.isEmpty()) {
                System.out.println("Content Cannot Be Empty");
                return RCode.error("Content Cannot Be Empty");
            }
            if(title == null || title.isEmpty()) {
                System.out.println("Title Cannot Be Empty");
                return RCode.error("Title Cannot Be Empty");
            }
            if(pic_post == null || pic_post.isEmpty()) pic_post = "http://defaultPostPic";
            Post post = new Post();
            post.setCustomer_id(customer.getCustomer_id());
            post.setTitle(title);
            post.setContent(content);
            post.setPic_post(pic_post);
            post.setTime_post(date);
            this.postService.insertPost(post);
            System.out.println("successful");
        }
        else {
            System.out.println("Failed");
            return RCode.error("Failed");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/delete"})
    public RCode deletePost(@RequestBody DeletePostRequest request) {
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post != null) {
            this.postService.deletePostByPostId(post_id);
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok("successful");
    }
    @PostMapping({"/mypost"})
    public RCode selectPostByCustomerId(@RequestBody SelectPostByCustomerIdRequest request) {
        BigInteger user_id = request.getUser_id();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        ArrayList<HashMap> list = new ArrayList<>();
        if(customer != null ) {
            list = this.postService.selectPostByCustomerId(customer.getCustomer_id());
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/homepage"})
    public RCode selectAllPostExceptMine(@RequestBody SelectPostByCustomerIdRequest request) {
        BigInteger user_id = request.getUser_id();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        ArrayList<HashMap> list = new ArrayList<>();
        if(customer != null) {
            list = this.postService.selectAllPostExceptMine(customer.getCustomer_id());
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/homepage/relative"})
    public RCode selectRelativePost(@RequestBody SelectRelativePostRequest request) {
        BigInteger user_id = request.getUser_id();
        String keyword = request.getKeyword();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        ArrayList<HashMap> list = new ArrayList<>();
        if(customer != null) {
            list = this.postService.selectRelativePost(keyword, customer.getCustomer_id());
            System.out.println("successful");
        }
        else {
            System.out.println("User Does Not Exist");
            return RCode.error("User Does Not Exist");
        }
        return RCode.ok().put("list", list);
    }
    @PostMapping({"/detail"})
    public RCode selectPostByPostId(@RequestBody SelectPostByPostIdRequest request) {
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post == null) {
            System.out.println("Post Does Not Exist");
            return RCode.error("Post Does Not Exist");
        }
        else {
            System.out.println("successful");
        }
        return RCode.ok().put("list", post);
    }
}
