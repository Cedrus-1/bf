package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.bean.ChatRecord;
import com.service.ChatRecordService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class ChatRecordController {
    @Autowired
    private ChatRecordService chatRecordService;

    @RequestMapping(value = "/getChatRecord",method = RequestMethod.POST ,produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String getChatRecordByUserID(int userID, HttpSession session){
        int ownUserID = (int)session.getAttribute("userID");
        Page<ChatRecord> pages = chatRecordService.getPageRecordByUserIDs(userID,ownUserID,1,20);
        List<ChatMessage> result = new ArrayList<>();

        for(ChatRecord chatRecord :pages.getPageDatas()){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setChatText(chatRecord.getChatRecord());
            if(chatRecord.getSendUserID() == ownUserID){
                chatMessage.setMe(true);
            }else {
                chatMessage.setMe(false);
            }
            result.add(chatMessage);
        }
        Collections.reverse(result);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(result);
        return jsonArray.toJSONString();
    }
}

class ChatMessage{
    private boolean isMe;
    private String chatText;

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }
}



