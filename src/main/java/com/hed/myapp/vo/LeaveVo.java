package com.hed.myapp.vo;

import java.io.Serializable;

public class LeaveVo implements Serializable {

	private static final long serialVersionUID = -1581505803368192222L;

	private String id;
	
	private String startTime;
	
	private String endTime;
	
	private String leaveType;
		
	private String reason;
	
	private String applyTime;
	
	private String realityStartTime;
	
	private String realityEndTime;
	
	private String disposeUser;
	
	private String userId;
	
	private String userName;
	
	private String currNode;
	
	private String processInstanceId;
	
	private String taskId;
	
	private String insId;
	
	private String defId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getRealityStartTime() {
		return realityStartTime;
	}

	public void setRealityStartTime(String realityStartTime) {
		this.realityStartTime = realityStartTime;
	}

	public String getRealityEndTime() {
		return realityEndTime;
	}

	public void setRealityEndTime(String realityEndTime) {
		this.realityEndTime = realityEndTime;
	}

	public String getDisposeUser() {
		return disposeUser;
	}

	public void setDisposeUser(String disposeUser) {
		this.disposeUser = disposeUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrNode() {
		return currNode;
	}

	public void setCurrNode(String currNode) {
		this.currNode = currNode;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getDefId() {
		return defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
