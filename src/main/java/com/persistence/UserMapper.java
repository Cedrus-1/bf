package com.persistence;

import com.bean.User;

public interface UserMapper {
	
	User getUserByID(int id);
	
	User getUserByName(String username);
	
	int addUser(User user);
	
	int updateUser(User user);

}
