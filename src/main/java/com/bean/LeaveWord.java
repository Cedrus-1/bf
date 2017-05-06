package com.bean;

import java.util.Date;

public class LeaveWord {
	private int leaveWordID;
	private int sendUserID;
	private int receiveUserID;
	private String leaveWord;
	private Date time;
	private int parentLeaveWordID;
	private int level;
	
	public int getLeaveWordID() {
		return leaveWordID;
	}
	public void setLeaveWordID(int leaveWordID) {
		this.leaveWordID = leaveWordID;
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
	public String getLeaveWord() {
		return leaveWord;
	}
	public void setLeaveWord(String leaveWord) {
		this.leaveWord = leaveWord;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getParentLeaveWordID() {
		return parentLeaveWordID;
	}
	public void setParentLeaveWordID(int parentLeaveWordID) {
		this.parentLeaveWordID = parentLeaveWordID;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	

	
	

}
