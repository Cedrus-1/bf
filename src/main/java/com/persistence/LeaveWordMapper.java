package com.persistence;

import java.util.List;

import com.bean.LeaveWord;

public interface LeaveWordMapper {
	
	List<LeaveWord> getPageLeaveWordsByUserID(int id, int start, int num);
	
	int getLeaveWordsCountByUserID(int id);
	
	int addLeaveWord(LeaveWord leaveWord);
	
	int deleteLeaveWordByID(int id);

}
