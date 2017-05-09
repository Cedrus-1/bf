package com.controller;

import com.bean.Dynamic;
import com.bean.User;
import com.enums.Message;
import com.enums.State;
import com.service.DynamicService;
import com.service.LeaveWordService;
import com.service.RelationService;
import com.service.UserService;
import com.util.Page;
import com.util.UploadUtil;
import com.websocket.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
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
    private LeaveWordService leaveWordService;

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("index");
        User user = userService.getUserByID((int) session.getAttribute("userID"));
        //推荐用户
        User query = new User();
        query.setAge(user.getAge());
        if(user.getSex() ==0){
            query.setSex(0);
        }else {
            query.setSex(3-user.getSex());
        }
        List<User> recommend = userService.getRandomUsersByUser(query,user.getUserID());
        modelAndView.addObject("user", user);
        modelAndView.addObject("recommend", recommend);
        return modelAndView;
    }

    //个人主页
    @RequestMapping("/profile")
    public ModelAndView userInfo(int userID) {
        ModelAndView modelAndView = new ModelAndView("profile");
        User user = userService.getUserByID(userID);
        modelAndView.addObject("user", user);
        return modelAndView;
    }



    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, RedirectAttributes attributes, HttpSession session) {
        try {
            int userID = (int) session.getAttribute("userID");
            String fileUrl = UploadUtil.upload(request, "upload", session.getAttribute("userID").toString());
            User user = userService.getUserByID(userID);
            user.setPhoto(fileUrl);
            Message message = userService.updateUser(user);
            if (message.getState() == State.SUCCESS) {
                attributes.addFlashAttribute("message", "头像更新成功!");
            } else {
                attributes.addFlashAttribute("error", "头像更新失败!");
            }
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "头像更新失败!");
        }
        return "redirect:/user/index";
    }

    /**
     * 获取用户头像
     *
     * @param userID
     */
    @RequestMapping(value = "/getHead")
    public void getHead(int userID, HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = userService.getUserByID(userID);
            String path = user.getPhoto();
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            String picturePath = rootPath + path;
            response.setContentType("image/jpeg; charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(picturePath);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
