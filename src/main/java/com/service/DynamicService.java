package com.service;


import com.bean.Dynamic;
import com.enums.Message;
import com.util.Page;

public interface DynamicService {
	
	Page<Dynamic> getPageDynamicByUserID(int id, int pageNum, int pageSize);
	
	Page<Dynamic> getOwnPageDynamicByUserID(int id, int pageNum, int pageSize);
	
	Message addDynamic(Dynamic dynamic);
	
	Message deleteDynamic(int dynamicID);

}
