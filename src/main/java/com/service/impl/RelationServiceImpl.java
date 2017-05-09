package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.persistence.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Relation;
import com.bean.User;
import com.enums.Message;
import com.enums.State;
import com.persistence.RelationMapper;
import com.persistence.UserMapper;
import com.service.RelationService;

@Service
public class RelationServiceImpl implements RelationService {
	@Autowired
	private RelationMapper relationMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public List<User> getFriendsByID(int id) {
		List<Relation> relations = relationMapper.getRelationsByID(id);
		return convertToUser(relations, id);
	}
	
	@Override
	public List<User> getApplyUsersByID(int id) {
		List<Relation> relations = relationMapper.getApplyRelationByID(id);
		return convertToUser(relations, id);
	}

	@Override
	public Message applyRelation(int applyID, int agreeID) {		
		Message message = new Message();
		if(applyID==agreeID){
			message.setState(State.ERROR);
			message.setMessage("你不能申请自己");
			return message;
		}
		Relation relation =  relationMapper.getRelationByUserIDs(applyID, agreeID);
		if(relation!=null){
			message.setState(State.ERROR);
			message.setMessage("你们已经是好友或者已经申请过了！");
			return message;
		}
		relation = new Relation();
		relation.setApplyID(applyID);
		relation.setAgreeID(agreeID);
		relation.setType(0);

		com.bean.Message message1 = new com.bean.Message();
		message1.setSendUserID(applyID);
		message1.setReceiveUserID(agreeID);
		message1.setType("好友申请");
		message1.setContent("好友申请");
		message1.setMessageTime(new Date());

		if(relationMapper.addRelation(relation)>0 && messageMapper.addMessage(message1)>0){
			message.setState(State.SUCCESS);
			message.setMessage("申请成功");
		}else{
			message.setState(State.ERROR);
			message.setMessage("申请失败");
		}
		return message;
	}

	@Override
	public Message agreeRelation(int applyID, int agreeID) {
		Message message = new Message();
		Relation relation =  relationMapper.getRelationByUserIDs(applyID, agreeID);
		if(relation==null){
			message.setState(State.ERROR);
			message.setMessage("没有记录，无法同意！");
			return message;
		}
		if(relation.getType()==1){
			message.setState(State.ERROR);
			message.setMessage("你们已经是好友了！");
			return message;
		}else{
			relation.setType(1);
			if(relationMapper.updateRelation(relation)>0){
				message.setState(State.SUCCESS);
				message.setMessage("成功添加好友！");
			}else{
				message.setState(State.ERROR);
				message.setMessage("好友添加失败，请稍候重试！");
			}
		}
		return message;
	}

	@Override
	public Message deleteRelation(int applyID, int agreeID) {
		Message message = new Message();
		Relation relation =  relationMapper.getRelationByUserIDs(applyID, agreeID);
		if(relation==null){
			message.setState(State.ERROR);
			message.setMessage("没有记录，无法删除！");
			return message;
		}
		if(relationMapper.deleteRelation(relation.getRelationID())>0){
			message.setState(State.SUCCESS);
			message.setMessage("删除成功！");
		}else{
			message.setState(State.ERROR);
			message.setMessage("删除失败，请稍候重试！");
		}
		return message;
	}
	
	private List<User> convertToUser(List<Relation> relations, int id){
		if(relations==null || relations.size()==0){
			return null;
		}
		List<User> users = new ArrayList<User>();
		User user = new User();
		for(Relation r: relations){
			if(r.getApplyID() == id){
				user = userMapper.getUserByID(r.getAgreeID());
			}else{
				user = userMapper.getUserByID(r.getApplyID());
			}
			users.add(user);
		}
		return users;
	}

	@Override
	public List<Integer> getFriendIDsByID(int id) {
		List<Relation> relations = relationMapper.getRelationsByID(id);
		List<Integer> result = new ArrayList<Integer>();
		for(Relation r: relations){
			if(r.getApplyID() == id){
				result.add(r.getAgreeID());
			}else{
				result.add(r.getApplyID());
			}
		}
		return result;
	}



}
