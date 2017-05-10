package com.controller;

import com.bean.Dynamic;
import com.bean.LeaveWord;
import com.bean.Relation;
import com.bean.User;
import com.enums.Message;
import com.enums.State;
import com.service.DynamicService;
import com.service.LeaveWordService;
import com.service.RelationService;
import com.service.UserService;
import com.sun.istack.internal.Nullable;
import com.util.Page;
import com.util.UploadUtil;
import com.websocket.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        query.setSex(user.getSex());
        List<User> recommend = userService.getRandomUsersByUser(query,user.getUserID());
        modelAndView.addObject("user", user);
        modelAndView.addObject("recommend", recommend);
        return modelAndView;
    }

    //个人主页
    @RequestMapping("/profile")
    public ModelAndView userInfo( int pageNum , HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("profile");
        int userID = (int)session.getAttribute("userID");
        User user = userService.getUserByID(userID);
        Page<List<LeaveWord>> pages = leaveWordService.getPageLeaveWordsByUserID(userID,pageNum,5);
        modelAndView.addObject("user", user);
        modelAndView.addObject("pages", pages);
        return modelAndView;
    }

    @RequestMapping("/hot")
    public ModelAndView hot(int pageNum) {
        ModelAndView modelAndView = new ModelAndView("hot");
        Page<Dynamic> pages = dynamicService.getPageDynamicByPage(pageNum,5);
        modelAndView.addObject("pages",pages);
        return modelAndView;
    }

    @RequestMapping("/setting")
    public ModelAndView setting(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("setting");
        User user = userService.getUserByID((int)session.getAttribute("userID"));
        modelAndView.addObject("user",user);
        return modelAndView;
    }


    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateInfo(HttpServletRequest request,HttpSession session, RedirectAttributes attributes) {
        User user = userService.getUserByID((int)session.getAttribute("userID"));
        String work = request.getParameter("work");
        int sex = Integer.parseInt(request.getParameter("sex"));
        String hometown = request.getParameter("hometown");
        String district = request.getParameter("district");
        String school = request.getParameter("school");
        String phone = request.getParameter("phone");
        String perSig = request.getParameter("perSig");
        String birthday = request.getParameter("birthday");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=sdf.parse(birthday);
            user.setBirth(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setSex(sex);
        user.setWork(work);
        user.setHometown(hometown);
        user.setDistrict(district);
        user.setSchool(school);
        user.setPhone(phone);
        user.setPersonalizedSignature(perSig);
        String fileUrl = UploadUtil.upload(request, "upload", String.valueOf(user.getUserID()));
        if (fileUrl != "") {
            user.setPhoto(fileUrl);
            session.setAttribute("photo",fileUrl);
        }

        Message message = userService.updateUser(user);
        if (message.getState() == State.SUCCESS) {
            attributes.addFlashAttribute("message", "资料更新成功!");
        } else {
            attributes.addFlashAttribute("error", "资料更新失败!");
        }
        return "redirect:/user/setting";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(String oldPassword, String newPassword,RedirectAttributes attributes,HttpSession session) {
        User user = userService.getUserByID((int)session.getAttribute("userID"));
        if(!user.getPassword().equals(oldPassword)){
            attributes.addFlashAttribute("error", "原密码错误!");
            return "redirect:/user/setting";
        }else {
            user.setPassword(newPassword);
            Message message = userService.updateUser(user);
            if (message.getState() == State.SUCCESS) {
                attributes.addFlashAttribute("message", "密码修改成功!");
                session.removeAttribute("userID");
                session.removeAttribute("userName");
                session.removeAttribute("photo");
                return "redirect:/login";
            } else {
                attributes.addFlashAttribute("error", "资料更新失败!");
                return "redirect:/user/setting";
            }
        }
    }

    @RequestMapping("/singlePage")
    public ModelAndView singlePage(int userID,HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("single-page");
        User user = userService.getUserByID(userID);
        List<User> friends = relationService.getFriendsByID((int)session.getAttribute("userID"));
        Page<List<LeaveWord>> pages = leaveWordService.getPageLeaveWordsByUserID(userID,1,5);
        if(pages.getPageDatas().size()>2){
            pages.setPageDatas(pages.getPageDatas().subList(0,2));
        }
        modelAndView.addObject("user", user);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("pages", pages);
        return modelAndView;
    }

    @RequestMapping("/findFriend")
    public ModelAndView findFriend(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("find-friend");
        User user = userService.getUserByID((int) session.getAttribute("userID"));
        //推荐用户
        User query = new User();
        query.setAge(user.getAge());
        query.setSex(user.getSex());
        List<User> recommend = userService.getRandomUsersByUser(query,user.getUserID());
        modelAndView.addObject("user", query);
        modelAndView.addObject("recommend", recommend);
        return modelAndView;
    }

    @RequestMapping(value = "/queryFriend",method = RequestMethod.POST)
    public ModelAndView queryFriend(int sex, int age, String work, String hometown, String district, String school, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("find-friend");
        int userID = (int)session.getAttribute("userID");
        User query = new User();
        query.setSex(sex);
        query.setAge(age);
        query.setWork(work);
        query.setHometown(hometown);
        query.setDistrict(district);
        query.setSchool(school);
        List<User> recommend = userService.queryUsers(query,userID);
        modelAndView.addObject("recommend",recommend);
        modelAndView.addObject("user",query);
        return modelAndView;
    }

    @RequestMapping("/chat")
    public ModelAndView chat(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("chat");
        int userID = (int)session.getAttribute("userID");
        List<User> friends = relationService.getFriendsByID(userID);
        if(friends==null||friends.size()==0){
            return modelAndView;
        }
        List<User> online = new ArrayList<>();
        Map<Integer,WebSocketSession> userMap = MyHandler.getUserMap();
        for(Integer integer:userMap.keySet()){
            online.add(userService.getUserByID(integer));
        }
        friends.removeAll(online);
        modelAndView.addObject("offline",friends);
        modelAndView.addObject("online",online);
        return modelAndView;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, RedirectAttributes attributes, HttpSession session) {
        try {
            int userID = (int) session.getAttribute("userID");
            User user = userService.getUserByID(userID);
            String fileUrl = UploadUtil.upload(request, "upload", String.valueOf(userID));
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
