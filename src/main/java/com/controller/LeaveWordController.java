package com.controller;

import com.bean.LeaveWord;
import com.enums.Message;
import com.enums.State;
import com.service.LeaveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * Created by Cedrus on 2017/5/9.
 */
@Controller
public class LeaveWordController {
    @Autowired
    private LeaveWordService leaveWordService;

    @RequestMapping(value = "/addLeaveWord", method = RequestMethod.POST)
    @ResponseBody
    public int addLeaveWord(int sendUserID, int receiveUserID, String content, int leaveWordID, RedirectAttributes attributes){
        LeaveWord leaveWord = new LeaveWord();
        leaveWord.setLeaveWord(content);
        leaveWord.setSendUserID(sendUserID);
        leaveWord.setReceiveUserID(receiveUserID);
        leaveWord.setParentLeaveWordID(leaveWordID);
        leaveWord.setTime(new Date());
        Message message = leaveWordService.addLeaveWord(leaveWord);
        if(message.getState() == State.SUCCESS){
            return 1;
        }else {
           return 0;
        }
    }

    @RequestMapping(value = "/deleteLeaveWord", method = RequestMethod.POST)
    @ResponseBody
    public int addLeaveWord(int uid){
        Message message = leaveWordService.deleteLeaveWord(uid);
        if(message.getState() == State.SUCCESS){
           return 1;
        }else {
         return 0;
        }
    }
}
