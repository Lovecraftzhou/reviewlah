package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeletePostCommentByPCIdRequest;
import com.reviewlah.controller.form.InsertPostCommentRequest;
import com.reviewlah.controller.form.SelectPostCommentByPostAndCusIdRequest;
import com.reviewlah.controller.form.SelectPostCommentByPostIdRequest;
import com.reviewlah.controller.form.UpdatePostCommentRequest;
import com.reviewlah.db.pojo.*;
import com.reviewlah.remote.PostCommentMessage;
import com.reviewlah.remote.MessageService;
import com.reviewlah.service.CustomerService;
import com.reviewlah.service.PostCommentService;
import com.reviewlah.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping({"/customer/post/detail"})
public class PostCommentController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostCommentService postCommentService;
    @Autowired
    private MessageService messageService;

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
    @PostMapping({"/post_comment"})
    public RCode selectPostCommentByPostAndCusId(@RequestBody SelectPostCommentByPostAndCusIdRequest request) {
        ArrayList<HashMap> res = new ArrayList<>();
        BigInteger post_id = request.getPost_id();
        BigInteger user_id = request.getUser_id();
        Post post = this.postService.selectPostByPostId(post_id);
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        if(post != null) {
            if(customer != null) {
                res = this.postCommentService.selectPostMapCommentByCusAndPostId(post_id, customer.getCustomer_id());
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
    @PostMapping({"/post_comment/insert"})
    public RCode insertPostComment(@RequestBody InsertPostCommentRequest request) {
        BigInteger user_id = request.getUser_id();
        BigInteger post_id = request.getPost_id();
        String content = request.getContent();
        Customer customer = this.customerService.selectCustomerByCustomerId(user_id);
        Post post = this.postService.selectPostByPostId(post_id);
        Date date = new Date();
        if(post != null) {
            if(customer != null) {
                if(content == null || content.isEmpty()) {
                    System.out.println("Content Cannot Be Empty");
                    return RCode.error("Content Cannot Be Empty");
                }
                PostComment postComment = new PostComment();
                postComment.setCustomer_id(customer.getCustomer_id());
                postComment.setPost_id(post_id);
                postComment.setContent(content);
                postComment.setTime_pc(date);
                this.postCommentService.insertPostComment(postComment);

                PostCommentMessage message = new PostCommentMessage();
                message.setPost_com_id(postComment.getPost_com_id());
                message.setPost_id(post_id);
                message.setCustomer_id(user_id);
                message.setContent(content);
                message.setTime(date);
                messageService.insertPostCommentMessage(message);
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

    @PostMapping({"/post_comment/update"})
    public RCode updatePostCommentByPCId(@RequestBody UpdatePostCommentRequest request) {
        PostComment postComment = new PostComment();
        postComment.setPost_com_id(request.getPost_com_id());
        postComment.setCustomer_id(request.getCustomer_id());
        postComment.setPost_id(request.getPost_id());
        postComment.setContent(request.getContent());
        postComment.setTime_pc(request.getTime_pc());
        postCommentService.updatePostCommentByPCId(postComment);
        return RCode.ok("successful");
    }
}
