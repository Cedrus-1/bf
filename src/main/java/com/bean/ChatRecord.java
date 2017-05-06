package com.bean;

import java.util.Date;

public class ChatRecord {
	private int chatRecordID;
	private int sendUserID;
	private int receiveUserID;
	private String chatRecord;
	private Date time;
	
	public int getChatRecordID() {
		return chatRecordID;
	}
	public void setChatRecordID(int chatRecordID) {
		this.chatRecordID = chatRecordID;
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
	public String getChatRecord() {
		return chatRecord;
	}
	public void setChatRecord(String chatRecord) {
		this.chatRecord = chatRecord;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
