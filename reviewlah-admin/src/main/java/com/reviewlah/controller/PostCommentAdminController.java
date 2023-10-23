package com.reviewlah.controller;

import java.math.BigInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.InsertPostCommentRequest;
import com.reviewlah.controller.form.UpdatePostCommentRequest;
import com.reviewlah.remote.DeletePostCommentByPCId;
import com.reviewlah.remote.InsertPostComment;
import com.reviewlah.remote.SelectPostCommentByPostId;
import com.reviewlah.remote.UpdatePostComment;
import com.reviewlah.remote.PostCommentService;

@RestController
@RequestMapping({"/admin/post"})
public class PostCommentAdminController {
    @Autowired
    private PostCommentService postCommentService;

    @GetMapping({"/{post_id}/comments"})
    public RCode getPostComment(@PathVariable BigInteger post_id) {
        SelectPostCommentByPostId request = new SelectPostCommentByPostId();
        request.setPost_id(post_id);
        return postCommentService.getPostComment(request);
    }

    @PostMapping({"/{post_id}/comments"})
    public RCode insertPostComment(@PathVariable BigInteger post_id,
            @RequestBody InsertPostCommentRequest request) {
        InsertPostComment comment = new InsertPostComment();
        comment.setPost_id(post_id);
        comment.setContent(request.getContent());
        comment.setUser_id(request.getCustomer_id());
        return postCommentService.insertPostComment(comment);
    }

    @DeleteMapping({"/comments/{post_com_id}"})
    public RCode deletePostCommentByPCId(@PathVariable BigInteger post_com_id) {
        DeletePostCommentByPCId request = new DeletePostCommentByPCId();
        request.setPost_com_id(post_com_id);
        return postCommentService.deletePostCommentByPCId(request);
    }

    @PutMapping({"/comments/{post_com_id}"})
    public RCode updatePostCommentByPCId(@PathVariable BigInteger post_com_id,
            @RequestBody UpdatePostCommentRequest request) {
        UpdatePostComment comment = new UpdatePostComment();
        BeanUtils.copyProperties(request, comment);
        comment.setPost_com_id(post_com_id);
        return postCommentService.updatePostCommentByPCId(comment);
    }
}
