package com.controller;

import com.bean.Comment;
import com.bean.Dynamic;
import com.enums.Message;
import com.enums.State;
import com.service.CommentService;
import com.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Cedrus on 2017/5/10.
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(int pageNum,int dynamicID, int receiveUserID,  String content, RedirectAttributes attributes, HttpSession session){
        Comment comment = new Comment();
        int sendUserID = (int) session.getAttribute("userID");
        comment.setCommentUserID(sendUserID);
        comment.setCommentToUserID(receiveUserID);
        comment.setComment(content);
        comment.setCommentTime(new Date());
        comment.setDynamicID(dynamicID);
        Message message = commentService.addComment(comment);
        if (message.getState() == State.SUCCESS) {
            attributes.addFlashAttribute("message", message.getMessage());
        } else {
            attributes.addFlashAttribute("error", message.getMessage());
        }
        return "redirect:/user/hot?pageNum="+pageNum;
    }
}
