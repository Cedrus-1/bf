package com.websocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.ChatRecord;
import com.bean.User;
import com.enums.Message;
import com.service.ChatRecordService;
import com.service.RelationService;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyHandler extends TextWebSocketHandler {
	
	private static final Map<Integer,WebSocketSession> userMap;

    @Autowired
    private UserService userService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private ChatRecordService chatRecordService;
	 
    static {
        userMap = new HashMap<>();
    }

    public static Map<Integer, WebSocketSession> getUserMap() {
        return userMap;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String _message = message.getPayload();
        JSONObject chat = JSON.parseObject(_message);
        String to = chat.get("to").toString();
        String content = chat.get("content").toString();
        int sendUserID = Integer.parseInt(session.getAttributes().get("userCd").toString());
        int receiveUserID = Integer.parseInt(to);
        chat.put("from",sendUserID);
        //sendMessageToUser(sendUserID,new TextMessage(_message));//发给自己
        sendMessageToUser(receiveUserID,new TextMessage(chat.toString()));//发给目的对象
        ChatRecord record = new ChatRecord();
        record.setTime(new Date());
        record.setChatRecord(content);
        record.setSendUserID(sendUserID);
        record.setReceiveUserID(receiveUserID);
        Message message2 = chatRecordService.addRecord(record);
        System.out.println(message2.getMessage());

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connect to the websocket success......");
        // 从session中取在线用户Cd
        int userID = (int) session.getAttributes().get("userCd");
        userMap.put(userID,session);
        /*User user = userService.getUserByID(userID);
        List<User> friends = relationService.getFriendsByID(userID);
        List<User> list = new ArrayList<>();
        for(Map.Entry<Integer,WebSocketSession> entry:userMap.entrySet()){
            for(User user1 :friends){
                if(entry.getKey() == user1.getUserID()){
                    list.add(user1);
                }
            }
        }
        String message = getMessage("", list);
        if(user!=null){
            session.sendMessage(new TextMessage(message));
        }*/
    }

    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        System.out.println("websocket connection closed......");
        userMap.remove(session.getAttributes().get("userCd"));
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("WebSocket:"+session.getAttributes().get("userCd")+" close connection");
        for (Map.Entry<Integer, WebSocketSession> entry : userMap.entrySet()) {
            if (entry.getValue().getAttributes().get("userCd") == session.getAttributes().get("userCd")) {
                userMap.remove(session.getAttributes().get("userCd"));
                System.out.println("WebSocket in staticMap:" + session.getAttributes().get("userCd") + " removed");
            }
        }
    }

    public String getMessage(String message, List list){
        JSONObject member = new JSONObject();
        member.put("message", message);
        member.put("list", list);
        return member.toString();
    }

    public void sendMessageToUser(int userCd, TextMessage message) {
        WebSocketSession session = userMap.get(userCd);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}