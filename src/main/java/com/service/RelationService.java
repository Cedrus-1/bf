package com.service;

import java.util.List;

import com.bean.User;
import com.enums.Message;

public interface RelationService {
	
	List<User> getFriendsByID(int id);
	
	List<Integer> getFriendIDsByID(int id);
	
	List<User> getApplyUsersByID(int id);
	
	Message applyRelation(int applyID, int agreeID);
	
	Message agreeRelation(int applyID, int agreeID);
	
	Message deleteRelation(int applyID, int agreeID);

}
