package com.service.impl;

import com.bean.Comment;
import com.bean.Dynamic;
import com.enums.Message;
import com.enums.State;
import com.persistence.CommentMapper;
import com.persistence.DynamicMapper;
import com.persistence.MessageMapper;
import com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Comment> getCommentsByDynamicID(int id) {
        return commentMapper.getCommentsByDynamicID(id);
    }

    @Override
    public Message addComment(Comment comment) {
        Message message = new Message();

        int num = commentMapper.getCommentsByDynamicID(comment.getDynamicID()).size();
        comment.setLevel(num+1);

        Dynamic dynamic = dynamicMapper.getDynamicByID(comment.getDynamicID());
        dynamic.setCommentNumber(dynamic.getCommentNumber() + 1);
        com.bean.Message message1 = new com.bean.Message();
        message1.setContent(comment.getComment());
        message1.setMessageTime(new Date());
        message1.setType("comment");
        message1.setSendUserID(comment.getCommentUserID());
        message1.setReceiveUserID(comment.getCommentToUserID());
        if (commentMapper.addComment(comment) > 0
                && dynamicMapper.updateDynamicCommentNumber(dynamic) > 0
                && messageMapper.addMessage(message1)>0) {
            message.setState(State.SUCCESS);
            message.setMessage("评论成功");
        } else {
            message.setState(State.ERROR);
            message.setMessage("评论失败！请稍候重试！");
        }
        return message;
    }

    @Override
    public Message deleteCommentByDynamicID(int id) {
        Message message = new Message();
        Dynamic dynamic = dynamicMapper.getDynamicByID(id);
        dynamic.setCommentNumber(dynamic.getCommentNumber() - 1);
        if (commentMapper.deleteCommentByDynamicID(id) > 0 && dynamicMapper.updateDynamicCommentNumber(dynamic) > 0) {
            message.setState(State.SUCCESS);
            message.setMessage("删除成功");
        } else {
            message.setState(State.ERROR);
            message.setMessage("删除失败！请稍候重试！");
        }
        return message;
    }
}
