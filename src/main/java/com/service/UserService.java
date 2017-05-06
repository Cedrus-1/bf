package com.service;

import com.bean.User;
import com.enums.Message;

public interface UserService {
	
	User getUserByID(int id);
	
	User getUserByName(String username);
	
	Message addUser(User user);
	
	Message updateUser(User user);

}
