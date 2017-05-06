package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.LeaveWord;
import com.enums.Message;
import com.enums.State;
import com.persistence.LeaveWordMapper;
import com.service.LeaveWordService;
import com.util.Page;

@Service
public class LeaveWordServiceImpl implements LeaveWordService {
	@Autowired
	private LeaveWordMapper leaveWordMapper;

	@Override
	public Page<LeaveWord> getPageLeaveWordsByUserID(int id, int pageNum, int pageSize) {
		Page<LeaveWord> result = new Page<LeaveWord>();
		pageNum = (pageNum>0)?pageNum:1;
		List<LeaveWord> query = leaveWordMapper.getPageLeaveWordsByUserID(id, (pageNum-1)*pageSize, pageSize);
		int totalRecords = leaveWordMapper.getLeaveWordsCountByUserID(id);
		result.setPageIndex(pageNum);
		result.setPageSize(pageSize);
		result.setTotalRecords(totalRecords);
		result.setPageDatas(query);
		return result;
	}

	@Override
	public Message addLeaveWord(LeaveWord leaveWord) {
		Message message = new Message();
		if(leaveWordMapper.addLeaveWord(leaveWord)>0){
			message.setState(State.SUCCESS);
			message.setMessage("留言成功");
		}else{
			message.setState(State.ERROR);
			message.setMessage("留言失败！请稍候再试");
		}
		return message;
	}



}
