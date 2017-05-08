package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.persistence.MessageMapper;
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
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public Page<List<LeaveWord>> getPageLeaveWordsByUserID(int id, int pageNum, int pageSize) {
		List<List<LeaveWord>> data = new ArrayList<>();
		Page<List<LeaveWord>> result = new Page<>();
		pageNum = (pageNum>0)?pageNum:1;
		List<LeaveWord> query = leaveWordMapper.getPageLeaveWordsByUserID(id, (pageNum-1)*pageSize, pageSize);
		for(LeaveWord leaveWord:query){
			List<LeaveWord> temp = leaveWordMapper.getLeaveWordCommentByID(leaveWord.getLeaveWordID());
			temp.add(leaveWord);
			data.add(temp);
		}
		int totalRecords = leaveWordMapper.getLeaveWordsCountByUserID(id);
		result.setPageIndex(pageNum);
		result.setPageSize(pageSize);
		result.setTotalRecords(totalRecords);
		result.setPageDatas(data);
		return result;
	}

	@Override
	public Message addLeaveWord(LeaveWord leaveWord) {
		Message message = new Message();
		if(leaveWord.getParentLeaveWordID()>0){
			int num = leaveWordMapper.getLeaveWordCommentByID(leaveWord.getParentLeaveWordID()).size();
			leaveWord.setLevel(num+1);
		}else {
			leaveWord.setLevel(0);
		}
		com.bean.Message message1 = new com.bean.Message();
		message1.setContent(leaveWord.getLeaveWord());
		message1.setMessageTime(new Date());
		message1.setType("comment");
		message1.setSendUserID(leaveWord.getSendUserID());
		message1.setReceiveUserID(leaveWord.getReceiveUserID());
		if(leaveWordMapper.addLeaveWord(leaveWord)>0
				&& messageMapper.addMessage(message1)>0) {
			message.setState(State.SUCCESS);
			message.setMessage("留言成功");
		}else{
			message.setState(State.ERROR);
			message.setMessage("留言失败！请稍候再试");
		}
		return message;
	}

	@Override
	public Message deleteLeaveWord(int leaveWordID) {
		Message message = new Message();
		if(leaveWordMapper.deleteLeaveWordByID(leaveWordID)>0){
			message.setState(State.SUCCESS);
			message.setMessage("删除成功");
		}else {
			message.setState(State.ERROR);
			message.setMessage("删除失败");
		}
		return message;
	}


}
