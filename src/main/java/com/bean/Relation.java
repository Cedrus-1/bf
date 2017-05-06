package com.bean;

public class Relation {
	private int relationID;
	private int applyID;//申请者
	private int agreeID;//同意者
	private int type;//1代表已经是好友，2代表申请中
	public int getRelationID() {
		return relationID;
	}
	public void setRelationID(int relationID) {
		this.relationID = relationID;
	}
	public int getApplyID() {
		return applyID;
	}
	public void setApplyID(int applyID) {
		this.applyID = applyID;
	}
	
	public int getAgreeID() {
		return agreeID;
	}
	public void setAgreeID(int agreeID) {
		this.agreeID = agreeID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
}
