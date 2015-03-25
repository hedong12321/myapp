package com.hed.myapp.dao;

import com.hed.myapp.entity.Leave;


public interface BaseDao {

	public String saveLeave(Leave leave);
	
	public boolean updateLeave(String id, String value);
	
	public Leave queryLeaveLst(String userId, String instanceId);
}
