package com.persistence;

import java.util.List;

import com.bean.ChatRecord;

public interface ChatRecordMapper {
	
	List<ChatRecord> getPageRecordByUserIDs(int oneID, int anotherID, int start, int num);
	int getPageCountRecordByUserIDs(int oneID, int anotherID);

	int addRecord(ChatRecord chatRecord);

	int deleteRecordsByIDs(int oneID, int anotherID);

}
