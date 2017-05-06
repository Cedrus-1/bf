package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.enums.Message;
import com.enums.State;
import com.persistence.UserMapper;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByName(String userName) {
		return userMapper.getUserByName(userName);
	}

	@Override
	public User getUserByID(int id) {
		return userMapper.getUserByID(id);
	}

	@Override
	public Message addUser(User user) {
		Message message = new Message();
		
		if(user.getPassword()==null || user.getPassword().trim()==""){
			message.setState(State.ERROR);
			message.setMessage("密码不能为空，请重新输入！");
			return message;
		}
		if(user.getUserName()==null || user.getUserName().trim()==""){
			message.setState(State.ERROR);
			message.setMessage("用户名不能为空，请重新输入！");
			return message;
		}
		User query = userMapper.getUserByName(user.getUserName());
		if(query==null){
			if(userMapper.addUser(user)>0){
				message.setState(State.SUCCESS);
				message.setMessage("注册成功！");
			}else{
				message.setState(State.ERROR);
				message.setMessage("注册失败！");
			}
		}else{
			message.setState(State.ERROR);
			message.setMessage("用户名已存在！");
		}
		return message;
	}

	@Override
	public Message updateUser(User user) {
		Message message = new Message();
		if(user.getPassword()!=null && user.getPassword().length() != 0 && user.getPassword().trim().length() == 0){
			message.setState(State.ERROR);
			message.setMessage("密码不能为空格！");
			return message;
		}
		if(userMapper.updateUser(user)>0){
			message.setState(State.SUCCESS);
			message.setMessage("更新成功！");
		}else{
			message.setState(State.ERROR);
			message.setMessage("更新失败！");
		}
		return message;
	}

}
