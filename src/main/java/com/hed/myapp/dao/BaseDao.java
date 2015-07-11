package com.hed.myapp.dao;

import com.hed.myapp.entity.Leave;


public interface BaseDao {

	/**
	 * �������
	 */
	public String saveLeave(Leave leave);
	
	/**
	 * ������٣��Ա�������ʵ��id
	 */
	public boolean updateLeave(String id, String value);
	
	/**
	 * ��ȡ��Ӧ�û�������ʵ������ٶ���
	 */
	public Leave queryLeaveByUsrIdAndInsId(String userId, String instanceId);
	
	/**
	 * ������ٶ���ID��ȡ��ٶ���
	 */
	public Leave getLeaveById(String id);
}
