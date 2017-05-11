package com.service.impl;

import com.bean.Message;
import com.persistence.MessageMapper;
import com.persistence.UserMapper;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Message getMessageByMessageID(int id) {
        return messageMapper.getMessageByMessageID(id);
    }

    @Override
    public List<Message> getFriendMessageByUserID(int id) {
        List<Message> messages = messageMapper.getFriendMessageByUserID(id);
        for(Message message:messages){
            message.setSendUser(userMapper.getUserByID(message.getSendUserID()));
        }
        return messages;
    }

    @Override
    public List<Message> getCommentMessageByUserID(int id) {
        List<Message> messages = messageMapper.getCommentMessageByUserID(id);
        for(Message message:messages){
            message.setSendUser(userMapper.getUserByID(message.getSendUserID()));
        }
        return messages;
    }

    @Override
    public List<Message> getLeaveWordMessageByUserID(int id) {
        List<Message> messages = messageMapper.getLeaveWordMessageByUserID(id);
        for(Message message:messages){
            message.setSendUser(userMapper.getUserByID(message.getSendUserID()));
        }
        return messages;
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
