package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Dynamic;
import com.bean.Like;
import com.enums.Message;
import com.enums.State;
import com.persistence.DynamicMapper;
import com.persistence.LikeMapper;
import com.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	private LikeMapper likeMapper;
	@Autowired
	private DynamicMapper dynamicMapper;
	
	@Override
	public Message confirmLike(int dynamicID, int userID) {
		Message message = new Message();
		Dynamic dynamic = dynamicMapper.getDynamicByID(dynamicID);
		if(dynamic==null){
			message.setState(State.ERROR);
			message.setMessage("动态不存在，无法点赞");
			return message;
		}
		Like query = likeMapper.getLikeByIDs(dynamicID, userID);
		if(query==null){
			dynamic.setLikeNumber(dynamic.getLikeNumber()+1);
			query = new Like();
			query.setDynamicID(dynamicID);
			query.setUserID(userID);
			if(likeMapper.confirmLike(query)>0 && dynamicMapper.updateDynamicLikeNumber(dynamic)>0){
				message.setState(State.SUCCESS);
				message.setMessage("点赞成功！");
			}else{
				message.setState(State.ERROR);
				message.setMessage("点赞失败，请稍候再试！");
			}
		}else{
			if(dynamic.getLikeNumber()>0){
				dynamic.setLikeNumber(dynamic.getLikeNumber()-1);
			}
			if(likeMapper.cancalLike(query)>0 && dynamicMapper.updateDynamicLikeNumber(dynamic)>0){
				message.setState(State.SUCCESS);
				message.setMessage("已经取消点赞！");
			}else{
				message.setState(State.ERROR);
				message.setMessage("点赞失败，请稍候再试！");
			}
			
		}
		return message;
	}

}
