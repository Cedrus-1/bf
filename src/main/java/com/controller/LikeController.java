package com.controller;

import com.bean.Dynamic;
import com.enums.Message;
import com.enums.State;
import com.service.DynamicService;
import com.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private DynamicService dynamicService;

    @RequestMapping(value = "/dynamicLike", method = RequestMethod.POST)
    @ResponseBody
    public int like(int dynamicUid, HttpSession session){
        int userID = (int)session.getAttribute("userID");
        Message message = likeService.confirmLike(dynamicUid,userID);
        if(message.getState()== State.SUCCESS){
            int likeNum = dynamicService.getDynamicByID(dynamicUid).getLikeNumber();
            return likeNum;
        }
        return -1;
    }
}
