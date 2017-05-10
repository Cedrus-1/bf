package com.controller;

import com.bean.Dynamic;
import com.bean.User;
import com.enums.Message;
import com.enums.State;
import com.service.DynamicService;
import com.service.UserService;
import com.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "addDynamic", method = RequestMethod.POST)
    public String addDynamic(HttpServletRequest request, String content, RedirectAttributes attributes, HttpSession session){
        try {
            int userID = (int) session.getAttribute("userID");
            String fileUrl = UploadUtil.upload(request, "dynamic", String.valueOf(userID));
            Dynamic dynamic = new Dynamic();
            dynamic.setContent(content);
            dynamic.setPhoto(fileUrl);
            dynamic.setDynamicTime(new Date());
            dynamic.setDynamicUserID(userID);
            Message message = dynamicService.addDynamic(dynamic);
            if (message.getState() == State.SUCCESS) {
                attributes.addFlashAttribute("message", message.getMessage());
            } else {
                attributes.addFlashAttribute("error", message.getMessage());
            }
        } catch (Exception e) {
            attributes.addFlashAttribute("error","添加动态失败！请稍候重试");
        }
        return "redirect:/user/hot?pageNum=1";
    }
}
