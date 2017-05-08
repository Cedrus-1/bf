package com.service.impl;

import com.bean.Message;
import com.persistence.MessageMapper;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message getMessageByMessageID(int id) {
        return messageMapper.getMessageByMessageID(id);
    }
    @Override
    public List<Message> getUserByUserID(int id) {
        return messageMapper.getUserByUserID(id);
    }

    @Override
    public boolean addMessage(Message message) {
       return messageMapper.addMessage(message)>0;
    }

    @Override
    public boolean updateMessage(int id) {
        return messageMapper.updateMessage(id)>0;
    }
}
