package com.hed.myapp.service;

import java.text.ParseException;
import java.util.List;

import org.activiti.engine.identity.User;

import com.hed.myapp.vo.LeaveVo;


public interface BaseService {

	public boolean saveLeave(LeaveVo leaveVo) throws ParseException;
	
	/**
	 * ��ȡδ�������ʵ��
	 */
	public List<LeaveVo> getUnfinished(User user);
	
	/**
	 * ��ȡ����������ʵ��
	 */
	public List<LeaveVo> getDealWith(User user);
}
