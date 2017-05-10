package com.persistence;

import com.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
	
	User getUserByID(int id);
	
	User getUserByName(String username);

	List<User> getRandomUsersByUser(User user);

	List<User> queryUsers(@Param("user") User user, int young, int old);
	
	int addUser(User user);
	
	int updateUser(User user);

}
