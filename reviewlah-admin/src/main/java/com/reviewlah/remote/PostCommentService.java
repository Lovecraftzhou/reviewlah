package com.reviewlah.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reviewlah.common.util.RCode;

@FeignClient("customer")
public interface PostCommentService {

    @GetMapping("/customer/post/detail/post_commentForPostOwner")
    RCode getPostComment(@RequestBody SelectPostCommentByPostId request);

    @PostMapping("/customer/post/detail/post_comment/insert")
    RCode insertPostComment(@RequestBody InsertPostComment request);

    @PostMapping("/customer/post/detail/post_comment/delete")
    RCode deletePostCommentByPCId(@RequestBody DeletePostCommentByPCId request);

    @PostMapping({"/customer/post/detail/post_comment/update"})
    RCode updatePostCommentByPCId(@RequestBody UpdatePostComment request);
}
