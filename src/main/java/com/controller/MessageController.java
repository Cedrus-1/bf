package com.controller;

import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "readMessage",method = RequestMethod.POST)
    @ResponseBody
    public int readMessage(int messageID){
        return messageService.updateMessage(messageID)?1:0;
    }
}
