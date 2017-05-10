package com.persistence;

import java.util.List;

import com.bean.Dynamic;

public interface DynamicMapper {

	List<Dynamic> getAllPageDynamic(int start, int num);
	int getAllDynamicCount();

	List<Dynamic> getPageDynamicByUserID(int id, int start, int num);
	int getDynamicCountByUserID(int id);
	
	List<Dynamic> getOwnPageDynamicByUserID(int id, int start, int num);
	int getOwnDynamicCountByUserID(int id);
	
	Dynamic getDynamicByID(int id);
	
	int addDynamic(Dynamic dynamic);
	
	int updateDynamicLikeNumber(Dynamic dynamic);
	
	int updateDynamicCommentNumber(Dynamic dynamic);
	
	int deleteDynamicByID(int id);
	
}
