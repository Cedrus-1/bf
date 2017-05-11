package com.controller;

import com.bean.Relation;
import com.enums.Message;
import com.enums.State;
import com.service.MessageService;
import com.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Cedrus on 2017/5/9.
 */
@Controller
public class RelationController {

    @Autowired
    private RelationService relationService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    @ResponseBody
    public int addFriend(int userID, HttpSession session) {
        int ownID = (int) session.getAttribute("userID");
        Message message = relationService.applyRelation(ownID,userID);
        if(message.getState()== State.SUCCESS){
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/agreeApply", method = RequestMethod.POST)
    @ResponseBody
    public int agreeApply(int messageID) {
        com.bean.Message message = messageService.getMessageByMessageID(messageID);
        Message message1 = relationService.agreeRelation(message.getSendUserID(),message.getReceiveUserID());

        if(message1.getState()== State.SUCCESS && messageService.updateMessage(messageID)){
            return 1;
        }
        return 0;
    }

    @RequestMapping(value = "/refuseApply", method = RequestMethod.POST)
    @ResponseBody
    public int refuseApply(int messageID) {
        com.bean.Message message = messageService.getMessageByMessageID(messageID);
        Message message1 = relationService.deleteRelation(message.getSendUserID(),message.getReceiveUserID());
        if(message1.getState()== State.SUCCESS && messageService.updateMessage(messageID)){
            return 1;
        }
        return 0;
    }
}
