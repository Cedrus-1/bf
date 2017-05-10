package com.service;

import com.bean.User;
import com.enums.Message;

import java.util.List;

public interface UserService {
	
	User getUserByID(int id);
	
	User getUserByName(String username);

	List<User> getRandomUsersByUser(User user,int userID);

	List<User> queryUsers(User user,int userID);
	
	Message addUser(User user);
	
	Message updateUser(User user);

}
