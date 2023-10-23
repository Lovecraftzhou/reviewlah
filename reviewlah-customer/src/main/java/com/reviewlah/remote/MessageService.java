package com.reviewlah.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.reviewlah.common.util.RCode;

@FeignClient("message")
public interface MessageService {

    @PostMapping("/message/post-comment")
    RCode insertPostCommentMessage(@RequestBody PostCommentMessage message);
}
