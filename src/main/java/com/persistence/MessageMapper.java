package com.persistence;

import com.bean.Message;
import com.bean.User;

import java.util.List;

public interface MessageMapper {
	
	Message getMessageByMessageID(int id);

	List<Message> getFriendMessageByUserID(int id);

	List<Message> getCommentMessageByUserID(int id);

	List<Message> getLeaveWordMessageByUserID(int id);
	
	int addMessage(Message message);

	int updateMessage(int id);

}
