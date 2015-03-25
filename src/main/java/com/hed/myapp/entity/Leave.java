package com.hed.myapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "leave")
public class Leave implements Serializable {

	private static final long serialVersionUID = 3040791632270475730L;

	@Id
	@Column(name = "id", nullable = false)
	//@SequenceGenerator(name="seq_leave", sequenceName="seq_leave", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_leave")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@GeneratedValue(generator = "uuidGenerator")
	private String id;
	
	@Column(name = "process_instance_id")
	private String processInstanceId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "leave_type")
	private String leaveType;
		
	@Column(name = "reason")
	private String reason;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apply_time")
	private Date applyTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reality_start_time")
	private Date realityStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reality_end_time")
	private Date realityEndTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getRealityStartTime() {
		return realityStartTime;
	}

	public void setRealityStartTime(Date realityStartTime) {
		this.realityStartTime = realityStartTime;
	}

	public Date getRealityEndTime() {
		return realityEndTime;
	}

	public void setRealityEndTime(Date realityEndTime) {
		this.realityEndTime = realityEndTime;
	}

}
