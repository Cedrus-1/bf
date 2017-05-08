package com.service;

import com.bean.LeaveWord;
import com.enums.Message;
import com.util.Page;

import java.util.List;

public interface LeaveWordService {
	
	Page<List<LeaveWord>> getPageLeaveWordsByUserID(int id, int pageNum, int pageSize);
	
	Message addLeaveWord(LeaveWord leaveWord);

	Message deleteLeaveWord(int leaveWordID);

}
