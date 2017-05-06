package com.service;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
	
	boolean login(String userName, String password, HttpServletRequest request);

}
