package com.service;

import com.bean.ChatRecord;
import com.enums.Message;
import com.util.Page;
import org.springframework.stereotype.Service;

public interface ChatRecordService {
    Page<ChatRecord> getPageRecordByUserIDs(int oneID, int anotherID, int pageNum, int pageSize);

    Message addRecord(ChatRecord chatRecord);

    Message deleteRecordsByIDs(int oneID, int anotherID);

}
