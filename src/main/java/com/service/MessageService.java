package com.service;

import com.bean.Message;
import java.util.List;

public interface MessageService {

	Message getMessageByMessageID(int id);

	List<Message> getFriendMessageByUserID(int id);

	List<Message> getCommentMessageByUserID(int id);

	List<Message> getLeaveWordMessageByUserID(int id);

	boolean addMessage(Message message);

	boolean updateMessage(int id);

}
