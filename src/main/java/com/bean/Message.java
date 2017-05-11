package com.bean;

import java.util.Date;

/**
 * Created by Cedrus on 2017/5/8.
 */
public class Message {
    private int messageID;
    private String type;
    private String content;
    private int sendUserID;
    private int receiveUserID;
    private Date messageTime;
    private int isRead;
    private User sendUser;

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSendUserID() {
        return sendUserID;
    }

    public void setSendUserID(int sendUserID) {
        this.sendUserID = sendUserID;
    }

    public int getReceiveUserID() {
        return receiveUserID;
    }

    public void setReceiveUserID(int receiveUserID) {
        this.receiveUserID = receiveUserID;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }
}
