package com.persistence;

import com.bean.User;

import java.util.List;

public interface UserMapper {
	
	User getUserByID(int id);
	
	User getUserByName(String username);

	List<User> getRandomUsersByUser(User user);
	
	int addUser(User user);
	
	int updateUser(User user);

}
