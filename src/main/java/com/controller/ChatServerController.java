package com.controller;/*package com.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.User;
import com.service.RelationService;
import com.service.UserService;
import com.websocket.HttpSessionConfigurator;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

*//**
 * websocket服务
 * @author  :  Amayadream
 * @time   :  2016.01.08 09:50
 *//*
@Controller
@RequestMapping(value = "/user")
@ServerEndpoint(value = "/chatServer", configurator = HttpSessionConfigurator.class)
public class ChatServerController {
	@Autowired
	private UserService userService;
	@Autowired
	private RelationService relationService;
	
    private static int onlineCount = 0; //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static CopyOnWriteArraySet<ChatServerController> webSocketSet = new CopyOnWriteArraySet<ChatServerController>();
    private Session session;    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private User user = new User();      //用户名
    private HttpSession httpSession;    //request的session

    private static List<Integer> list = new ArrayList<Integer>();   //在线列表,记录用户名称
    //private static Map routetab = new HashMap<>();  //用户名和websocket的session绑定的路由表
    private static Map<Integer,Map<Integer,Session>> routetab = new HashMap<Integer,Map<Integer,Session>>();  //用户名和websocket的session绑定的路由表

    *//**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     *//*
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        int userid=(Integer) httpSession.getAttribute("userid");    //获取当前用户id
        User user = userService.getUserByID(userid);
        this.user = user;
        List<Integer> userIDs = relationService.getFriendIDsByID(userid);//获取好友列表
        userIDs.containsAll(list);//取好友列表和在线列表的交集，即得到在线好友列表
        Map<Integer,Session> userFriendsTab = new HashMap<Integer, Session>();
        if(userIDs!=null && userIDs.size()>0){    	
        	for(Integer id : userIDs){
        		userFriendsTab.put(id, session);
        	}
        }
        userFriendsTab.put(userid, session);
        list.add(user.getUserID());           //将用户名加入在线列表
        routetab.put(user.getUserID(), userFriendsTab);   //将用户名和session绑定到路由表
        String message = getMessage("[" + user.getUserName() + "]上线了", "notice",  list);
        //broadcast(message);     //广播
        broadcastToFriendsByID(message,user.getUserID());
    }

    *//**
     * 连接关闭调用的方法
     *//*
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        list.remove(user.getUserID());        //从在线列表移除这个用户
        routetab.remove(user.getUserID());
        String message = getMessage("[" + user.getUserName() +"] 下线了", "notice", list);
        //broadcast(message);         //广播
        broadcastToFriendsByID(message,user.getUserID());
    }
    
    
    public void broadcastToFriendsByID(String message, int userID){
    	Map<Integer,Session> map = (Map<Integer,Session>)routetab.get(userID);
    	if(!map.isEmpty()){		
    		for(Map.Entry<Integer,Session> entry : map.entrySet()){
    			singleSend(message,entry.getValue());
    		}
    	}
    }

    *//**
     * 接收客户端的message,判断是否有接收人而选择进行广播还是指定发送
     * @param _message 客户端发送过来的消息
     *//*
    @OnMessage
    public void onMessage(String _message) {
        JSONObject chat = JSONObject.fromObject(_message);
        JSONObject message = JSONObject.fromObject(chat.get("message").toString());
        int userid = (int) message.get("to");
        Map<Integer,Session> map = (Map<Integer,Session>)routetab.get(message.get("from"));
        for(Map.Entry<Integer,Session> entry : map.entrySet()){
			singleSend(_message,entry.getValue());
		}
    }

    *//**
     * 发生错误时调用
     * @param error
     *//*
    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }

    *//**
     * 广播消息
     * @param message
     *//*
    public void broadcast(String message){
        for(ChatServerController chat: webSocketSet){
            try {
                chat.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    *//**
     * 对特定用户发送消息
     * @param message
     * @param session
     *//*
    public void singleSend(String message, Session session){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    *//**
     * 组装返回给前台的消息
     * @param message   交互信息
     * @param type      信息类型
     * @param list      在线列表
     * @return
     *//*
    public String getMessage(String message, String type, List list){
        JSONObject member = new JSONObject();
        member.put("message", message);
        member.put("type", type);
        member.put("list", list);
        return member.toString();
    }

    public  int getOnlineCount() {
        return onlineCount;
    }

    public  void addOnlineCount() {
        ChatServerController.onlineCount++;
    }

    public  void subOnlineCount() {
        ChatServerController.onlineCount--;
    }
}
*/