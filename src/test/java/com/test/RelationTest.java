package com.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.User;
import com.enums.Message;
import com.service.RelationService;

public class RelationTest extends BaseTest{
	@Autowired
	private RelationService relationService;
	
	@Test
	public void getFriendsByIDTest(){
		List<User> users = relationService.getFriendsByID(2);
		if(users==null|| users.size()==0){
			System.out.println("empty");
		}else{
			for(User user:users){
				System.out.println(user.toString());
			}
		}
	}
	
	@Test
	public void getApplyUsersByIDTest(){
		List<User> users = relationService.getApplyUsersByID(2);
		if(users==null|| users.size()==0){
			System.out.println("empty");
		}else{
			for(User user:users){
				System.out.println(user.toString());
			}
		}
	}
	
	@Test
	public void applyRelationTest(){
		Message message = relationService.applyRelation(3,2);
		System.out.println(message.getState()+" "+message.getMessage());
	}
	
	@Test
	public void agreeRelationTest(){
		Message message = relationService.agreeRelation(3,2);
		System.out.println(message.getState()+" "+message.getMessage());
	}

}
