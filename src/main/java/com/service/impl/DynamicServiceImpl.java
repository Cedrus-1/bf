package com.service.impl;

import com.bean.Comment;
import com.bean.Dynamic;
import com.enums.Message;
import com.enums.State;
import com.persistence.CommentMapper;
import com.persistence.DynamicMapper;
import com.service.DynamicService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Page<Dynamic> getPageDynamicByUserID(int id, int pageNum, int pageSize) {
        pageNum = (pageNum > 0) ? pageNum : 1;
        List<Dynamic> query = dynamicMapper.getPageDynamicByUserID(id, (pageNum - 1) * pageSize, pageSize);
        for(Dynamic dynamic:query){
            List<Comment> comments = commentMapper.getCommentsByDynamicID(dynamic.getDynamicID());
            dynamic.setComments(comments);
        }
        int count = dynamicMapper.getDynamicCountByUserID(id);
        Page<Dynamic> result = new Page<Dynamic>();
        result.setPageIndex(pageNum);
        result.setPageSize(pageSize);
        result.setTotalRecords(count);
        result.setPageDatas(query);
        return result;
    }

    @Override
    public Page<Dynamic> getOwnPageDynamicByUserID(int id, int pageNum, int pageSize) {
        pageNum = (pageNum > 0) ? pageNum : 1;
        List<Dynamic> query = dynamicMapper.getOwnPageDynamicByUserID(id, (pageNum - 1) * pageSize, pageSize);
        int count = dynamicMapper.getOwnDynamicCountByUserID(id);
        Page<Dynamic> result = new Page<Dynamic>();
        result.setPageIndex(pageNum);
        result.setPageSize(pageSize);
        result.setTotalRecords(count);
        result.setPageDatas(query);
        return result;
    }

    @Override
    public Message addDynamic(Dynamic dynamic) {
        Message message = new Message();
        if (dynamicMapper.addDynamic(dynamic) > 0) {
            message.setState(State.SUCCESS);
            message.setMessage("发表动态成功");
        } else {
            message.setState(State.ERROR);
            message.setMessage("发表动态失败！请稍候重试！");
        }
        return message;
    }

    @Override
    public Message deleteDynamic(int dynamicID) {
        Message message = new Message();
        if (dynamicMapper.deleteDynamicByID(dynamicID) > 0) {
            message.setState(State.SUCCESS);
            message.setMessage("动态删除成功");
        } else {
            message.setState(State.ERROR);
            message.setMessage("动态删除失败！请稍候重试！");
        }
        return message;
    }


}
