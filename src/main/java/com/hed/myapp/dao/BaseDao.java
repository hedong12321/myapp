package com.hed.myapp.dao;

import com.hed.myapp.entity.Leave;


public interface BaseDao {

	/**
	 * 保存请假
	 */
	public String saveLeave(Leave leave);
	
	/**
	 * 更新请假，以保存流程实例id
	 */
	public boolean updateLeave(String id, String value);
	
	/**
	 * 获取对应用户和流程实例的请假对象
	 */
	public Leave queryLeaveByUsrIdAndInsId(String userId, String instanceId);
	
	/**
	 * 根据请假对象ID获取请假对象
	 */
	public Leave getLeaveById(String id);
}
