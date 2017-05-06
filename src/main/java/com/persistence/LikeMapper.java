package com.persistence;

import java.util.List;

import com.bean.Like;

public interface LikeMapper {
	
	List<Like> getLikesByDynamicID(int id);
	
	Like getLikeByIDs(int dynamicID, int userID);
	
	int confirmLike(Like like);
	
	int cancalLike(Like like);

}
