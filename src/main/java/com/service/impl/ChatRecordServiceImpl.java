package com.service.impl;

import com.bean.ChatRecord;
import com.enums.Message;
import com.enums.State;
import com.persistence.ChatRecordMapper;
import com.service.ChatRecordService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatRecordServiceImpl implements ChatRecordService{
    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Override
    public Page<ChatRecord> getPageRecordByUserIDs(int oneID, int anotherID, int pageNum, int pageSize) {
        pageNum = (pageNum>0)?pageNum:1;
        List<ChatRecord> query = chatRecordMapper.getPageRecordByUserIDs(oneID, anotherID,(pageNum-1)*pageSize, pageSize);
        int count = chatRecordMapper.getPageCountRecordByUserIDs(oneID,anotherID);
        Page<ChatRecord> result = new Page<ChatRecord>();
        result.setPageIndex(pageNum);
        result.setPageSize(pageSize);
        result.setTotalRecords(count);
        result.setPageDatas(query);
        return result;
    }

    @Transactional
    @Override
    public Message addRecord(ChatRecord chatRecord) {
        Message message = new Message();
        if(chatRecordMapper.addRecord(chatRecord)>0){
            message.setState(State.SUCCESS);
            message.setMessage("插入成功");
        }else {
            message.setState(State.ERROR);
            message.setMessage("插入失败");
        }
        return message;
    }

    @Override
    public Message deleteRecordsByIDs(int oneID, int anotherID) {
        Message message = new Message();
        if(chatRecordMapper.deleteRecordsByIDs(oneID,anotherID)>0){
            message.setState(State.SUCCESS);
            message.setMessage("删除成功");
        }else {
            message.setState(State.ERROR);
            message.setMessage("删除失败");
        }
        return message;
    }
}
