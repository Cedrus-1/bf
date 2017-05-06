package com.service;

import com.bean.LeaveWord;
import com.enums.Message;
import com.util.Page;

public interface LeaveWordService {
	
	Page<LeaveWord> getPageLeaveWordsByUserID(int id, int pageNum, int pageSize);
	
	Message addLeaveWord(LeaveWord leaveWord);

}
