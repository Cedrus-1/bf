package com.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Dynamic;
import com.service.DynamicService;
import com.util.Page;

public class DynamicTest extends BaseTest{
	@Autowired
	private DynamicService dynamicService;
	
	@Test
	public void test(){
		Page<Dynamic> dynamics = dynamicService.getPageDynamicByUserID(1, 1, 20);
		for(Dynamic d : dynamics.getPageDatas()){
			System.out.println(d.getContent());
		}
	}

}
