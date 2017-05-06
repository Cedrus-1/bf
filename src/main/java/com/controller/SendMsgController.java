package com.controller;

import com.websocket.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SendMsgController {


    @Autowired
    private MyHandler myHandler;

    @RequestMapping("/tt")
    public String sendMsgToUser(HttpServletRequest request) {
        String msg = "2016，与你相遇，猴幸运！";
        int userCd = (int) request.getSession().getAttribute("USER_CD");
        myHandler.sendMessageToUser(userCd, new TextMessage(msg));
        return "redirect:/web";
    }
}
