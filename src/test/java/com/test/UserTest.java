package com.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.User;
import com.enums.Constellation;
import com.enums.Message;
import com.enums.State;
import com.service.UserService;

public class UserTest extends BaseTest{
	@Autowired
	private UserService userService;
	
	@Test
	public void test(){
		User user = new User();
		user.setUserName("222");
		user.setPassword(" ");
		//user.setEmail("232");
		user.setUserID(11);
		user = userService.getUserByID(2);
		
		/*Message message= userService.updateUser(user);
		if(message.getState()==State.SUCCESS){
			System.out.println(message.getMessage());
		}else{
			System.out.println(message.getMessage());
		}*/
	}

}
