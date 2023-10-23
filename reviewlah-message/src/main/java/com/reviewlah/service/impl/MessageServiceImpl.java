package com.reviewlah.service.impl;

import com.reviewlah.db.dao.MessageDao;
import com.reviewlah.db.pojo.PostCommentMessage;
import com.reviewlah.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public void insertPostComment(PostCommentMessage message) {
        messageDao.insertPostComment(message);
    }
}
