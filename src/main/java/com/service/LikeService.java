package com.service;

import com.enums.Message;

public interface LikeService {
	
	Message confirmLike(int dynamicID, int userID);

}
