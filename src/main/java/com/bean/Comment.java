package com.bean;

import java.util.Date;

public class Comment {
    private int commentID;
    private int dynamicID;
    private int commentUserID;
    private int commentToUserID;
    private int parentCommentID;
    private int level;
    private String comment;
    private Date commentTime;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getDynamicID() {
        return dynamicID;
    }

    public void setDynamicID(int dynamicID) {
        this.dynamicID = dynamicID;
    }

    public int getCommentUserID() {
        return commentUserID;
    }

    public void setCommentUserID(int commentUserID) {
        this.commentUserID = commentUserID;
    }

    public int getCommentToUserID() {
        return commentToUserID;
    }

    public void setCommentToUserID(int commentToUserID) {
        this.commentToUserID = commentToUserID;
    }

    public int getParentCommentID() {
        return parentCommentID;
    }

    public void setParentCommentID(int parentCommentID) {
        this.parentCommentID = parentCommentID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
