package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeletePostCommentByPCIdRequest;
import com.reviewlah.controller.form.InsertPostCommentRequest;
import com.reviewlah.controller.form.SelectPostCommentByPostAndCusIdRequest;
import com.reviewlah.controller.form.SelectPostCommentByPostIdRequest;
import com.reviewlah.db.pojo.*;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.PostCommentService;
import com.reviewlah.service.PostService;
import com.reviewlah.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.*;

@Tag(name = "评论模块")
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
    @Operation(summary = "获取帖主发的帖子")
    @PostMapping({"/post_commentForPostOwner"})
    public RCode selectPostCommentByPostId(@RequestBody SelectPostCommentByPostIdRequest request) {
        ArrayList<HashMap> res = new ArrayList<>();
        BigInteger post_id = request.getPost_id();
        Post post = this.postService.selectPostByPostId(post_id);
        if(post != null) {
            res = this.postCommentService.selectPostMapCommentByPostId(post_id);
        }
        else {
            System.out.println("Post Does Not Exist");
            return RCode.error("Post Does Not Exist");
        }
        return RCode.ok().put("list", res);
    }

    @Operation(summary = "获取当前用户发的帖子")
    @PostMapping({"/post_comment"})
    public RCode selectPostCommentByPostAndCusId(@RequestBody SelectPostCommentByPostAndCusIdRequest request) {
        ArrayList<HashMap> res = new ArrayList<>();
        BigInteger post_id = request.getPost_id();
        BigInteger user_id = request.getUser_id();
        Post post = this.postService.selectPostByPostId(post_id);
        User user = this.userService.selectUserById(user_id);
        if(post != null) {
            if(user != null) {
                Customer customer = this.customerService.selectCustomerByUserId(user_id);
                if(customer != null) {
                    res = this.postCommentService.selectPostMapCommentByCusAndPostId(post_id, customer.getCustomer_id());
                }
                else {
                    System.out.println("Customer Does Not Exist");
                    return RCode.error("Customer Does Not Exist");
                }
            }
            else {
                System.out.println("User Does Not Exist");
                return RCode.error("User Does Not Exist");
            }
        }
        else {
            System.out.println("Post Does Not Exist");
            return RCode.error("Post Does Not Exist");
        }
        return RCode.ok().put("list", res);
    }
//    public ArrayList<Map<String, Object>> selectPostCommentByPostId(@RequestBody SelectPostCommentByPostIdRequest request) {
//        ArrayList<Map<String, Object>> res = new ArrayList<>();
//        BigInteger post_id = request.getPost_id();
//        Post post = this.postService.selectPostByPostId(post_id);
//        if(post != null) {
//            ArrayList<PostComment> list = this.postCommentService.selectPostCommentByPostId(post_id);
//            for(PostComment tmp : list) {
//                BigInteger customer_id = tmp.getCustomer_id();
//                BigInteger user_id = this.customerService.selectUserIdByCustomerId(customer_id);
//                User user = this.userService.selectUserById(user_id);
//                String name = user.getName();
//                String avator = user.getAvator();
////                if(user == null) {
////                    name = "New Glory";
////                    avator = "";
////                }
//                Map<String, Object> map = new HashMap<>();
//                map.put("name", name);
//                map.put("avator", avator);
//                map.put("content", tmp.getContent());
//                map.put("time_pc", tmp.getTime_pc());
//                res.add(map);
//            }
//
//        }
//        else {
//            System.out.println("Post Does Not Exist");
//            return null;
//        }
//        return res;
//    }
    @Operation(summary = "发布评论")
    @PostMapping({"/post_comment/insert"})
    public RCode insertPostComment(@RequestBody InsertPostCommentRequest request) {
        BigInteger user_id = request.getUser_id();
        BigInteger post_id = request.getPost_id();
        String content = request.getContent();
        User user = this.userService.selectUserById(user_id);
        Post post = this.postService.selectPostByPostId(post_id);
        Date date = new Date();
        if(post != null) {
            if(user != null) {
                if(content == null || content.isEmpty()) {
                    System.out.println("Content Cannot Be Empty");
                    return RCode.error("Content Cannot Be Empty");
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
                System.out.println("User Does Not Exist");
                return RCode.error("User Does Not Exist");
            }
        }
        else {
            System.out.println("Post Does Not Exist");
            return RCode.error("Post Does Not Exist");
        }
        return RCode.ok("successful");
    }

    @Operation(summary = "删除评论")
    @PostMapping({"/post_comment/delete"})
    public RCode deletePostCommentByPCId(@RequestBody DeletePostCommentByPCIdRequest request) {
        BigInteger post_com_id = request.getPost_com_id();
        PostComment postComment = this.postCommentService.selectPostCommentByPCId(post_com_id);
        if(postComment != null) {
            this.postCommentService.deletePostCommentByPCId(post_com_id);
            System.out.println("successful");
        }
        else {
            System.out.println("PostComment Does Not Exist");
            return RCode.error("PostComment Does Not Exist");
        }
        return RCode.ok("successful");
    }
}
