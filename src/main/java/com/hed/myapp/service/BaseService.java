package com.hed.myapp.service;

import java.text.ParseException;
import java.util.List;

import org.activiti.engine.identity.User;

import com.hed.myapp.vo.LeaveVo;


public interface BaseService {

	public boolean saveLeave(LeaveVo leaveVo) throws ParseException;
	
	/**
	 * 获取未完成流程实例
	 */
	public List<LeaveVo> getUnfinished(User user);
	
	/**
	 * 获取待处理流程实例
	 */
	public List<LeaveVo> getDealWith(User user);
}
