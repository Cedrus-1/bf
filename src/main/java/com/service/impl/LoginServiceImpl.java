package com.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.persistence.UserMapper;
import com.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean login(String username, String password,HttpServletRequest request) {
		User user = userMapper.getUserByName(username);
		if(user!=null && user.getPassword().equals(password)){
			//RoleUtil.addUserRole(request.getSession(), user);
			return true;
		}else{			
			return false;
		}
	}

}
