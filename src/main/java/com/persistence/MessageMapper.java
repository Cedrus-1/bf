package com.persistence;

import com.bean.Message;
import com.bean.User;

import java.util.List;

public interface MessageMapper {
	
	Message getMessageByMessageID(int id);
	
	List<Message> getUserByUserID(int id);
	
	int addMessage(Message message);

	int updateMessage(int id);

}
