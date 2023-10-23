package com.reviewlah.controller;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.PostCommentMessageRequest;
import com.reviewlah.db.pojo.PostCommentMessage;
import com.reviewlah.service.MessageService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/message"})
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping({"/post-comment"})
    public RCode insertPostComment(@RequestBody PostCommentMessageRequest request) {
        PostCommentMessage message = new PostCommentMessage();
        BeanUtils.copyProperties(request, message);
        messageService.insertPostComment(message);
        return RCode.ok("successful");
    }
}
