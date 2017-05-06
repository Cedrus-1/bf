package com.service;


import com.bean.Comment;
import com.enums.Message;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByDynamicID(int id);

    Message addComment(Comment comment);

    Message deleteCommentByDynamicID(int id);

}
