package com.controller;

import com.enums.Message;
import com.enums.State;
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

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    @ResponseBody
    public int updateInfo(int userID, HttpSession session) {
        int ownID = (int) session.getAttribute("userID");
        Message message = relationService.applyRelation(ownID,userID);
        if(message.getState()== State.SUCCESS){
            return 1;
        }
        return 0;
    }
}
