package com.controller;

import com.bean.Dynamic;
import com.bean.User;
import com.service.CommentService;
import com.service.DynamicService;
import com.service.RelationService;
import com.service.UserService;
import com.util.Page;
import com.websocket.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private MyHandler myHandler;
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("index");
        User user = userService.getUserByID((int)session.getAttribute("userID"));
        List<User> friends = relationService.getFriendsByID(user.getUserID());
        Page<Dynamic> dynamicPage = dynamicService.getPageDynamicByUserID((int)session.getAttribute("userID"),1,20);
        modelAndView.addObject("user",user);
        modelAndView.addObject("dynamicPage",dynamicPage);
        modelAndView.addObject("friends",friends);
        return modelAndView;
    }

    @RequestMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("userInfo");
        User user = userService.getUserByID((int)session.getAttribute("userID"));
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public String updateInfo(String email, int sex, HttpSession session){
        User user = userService.getUserByID((int)session.getAttribute("userID"));
        return  "";
    }

}
