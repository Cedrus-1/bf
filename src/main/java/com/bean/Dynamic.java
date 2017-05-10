package com.bean;

import java.util.Date;
import java.util.List;

public class Dynamic {
	private int dynamicID;
	private int dynamicUserID;
	private String content;
	private Date dynamicTime;
	private int likeNumber;
	private int commentNumber;
	private String photo;
	private List<Comment> comments;
	private User dynamicUser;
	
	public int getDynamicID() {
		return dynamicID;
	}
	public void setDynamicID(int dynamicID) {
		this.dynamicID = dynamicID;
	}
	public int getDynamicUserID() {
		return dynamicUserID;
	}
	public void setDynamicUserID(int dynamicUserID) {
		this.dynamicUserID = dynamicUserID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDynamicTime() {
		return dynamicTime;
	}
	public void setDynamicTime(Date dynamicTime) {
		this.dynamicTime = dynamicTime;
	}
	public int getLikeNumber() {
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getDynamicUser() {
		return dynamicUser;
	}

	public void setDynamicUser(User dynamicUser) {
		this.dynamicUser = dynamicUser;
	}
}
