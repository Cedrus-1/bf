package com.controller;

import com.bean.User;
import com.enums.Message;
import com.enums.State;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes) {
        User user = userService.getUserByName(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("userID", user.getUserID());
            session.setAttribute("username", user.getUserName());
            attributes.addFlashAttribute("message", "SUCCESS");
            return "redirect:/index";
        } else {
            attributes.addFlashAttribute("error1", "用户名或密码错误");
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(String username, String password, String email, HttpSession session, RedirectAttributes attributes) {
        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        Message msg = userService.addUser(user);
        if (msg.getState() == State.SUCCESS) {
            session.setAttribute("userID", user.getUserID());
            session.setAttribute("username", user.getUserName());
            return "redirect:/index";
        } else {
            attributes.addFlashAttribute("error2", "此用户名太火了，已经被注册了");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userID");
        session.removeAttribute("username");
        return "redirect:/login";
    }


}
