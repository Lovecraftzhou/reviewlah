package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeletePostRequest;
import com.reviewlah.controller.form.InsertPostRequest;
import com.reviewlah.controller.form.SelectPostByCustomerIdRequest;
import com.reviewlah.controller.form.SelectPostByPostIdRequest;
import com.reviewlah.remote.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin/post"})
public class PostAdminController {
    @Autowired
    private PostService postService;

    @PostMapping({"/insert"})
    public RCode insertPost(@RequestBody InsertPostRequest request) {
        return this.postService.insertPost(request);
    }
    @PostMapping({"/delete"})
    public RCode deletePost(@RequestBody DeletePostRequest request) {
        return this.postService.deletePost(request);
    }
    @PostMapping({"/mypost"})
    public RCode selectPostByCustomerId(@RequestBody SelectPostByCustomerIdRequest request) {
        return this.postService.selectPostByCustomerId(request);
    }
    @PostMapping({"/detail"})
    public RCode selectPostByPostId(@RequestBody SelectPostByPostIdRequest request) {
        return this.postService.selectPostByPostId(request);
    }
}
