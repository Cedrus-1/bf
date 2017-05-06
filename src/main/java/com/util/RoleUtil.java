package com.util;

import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;


public class RoleUtil {
	
	public static final String USER_ROLE_NAME = "user";

	public static final String ROLE_NAME_KEY = "role";
	
	public static boolean isUser(HttpSession session){
		Object user = session.getAttribute(USER_ROLE_NAME);
		if(user == null)
			return false;
		else
			return true;
	}

	public static void addUserRole(HttpSession session, Object user){
		Assert.notNull(user, "the user must not be null");
		
		addRole(session, user, USER_ROLE_NAME);
	}
	
	private static void addRole(HttpSession session, Object obj, String roleName){
		session.setAttribute(roleName, obj);
		session.setAttribute(ROLE_NAME_KEY, roleName);
	}
	
	
}
