package com.persistence;

import com.bean.ChatRecord;
import com.bean.Comment;

import java.util.List;

public interface CommentMapper {
	
	List<Comment> getCommentsByDynamicID(int id);

	int addComment(Comment comment);

	int deleteCommentByDynamicID(int id);

}
