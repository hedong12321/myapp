package com.hed.myapp.action;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;

import com.hed.myapp.service.BaseService;
import com.hed.myapp.vo.LeaveVo;

public class ActivitiAction extends BaseAction {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -1077653780587008630L;

	@Resource(name="baseService")
	private BaseService baseService;
	
	@Resource(name="identityService")
	private IdentityService identityService;
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	private String email;
	
	private String password;
	
	private LeaveVo leave;
	
	private Map<String, Object> params;
	
	private List<LeaveVo> voLst;
	
	private List<LeaveVo> dealWithLst;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LeaveVo getLeave() {
		return leave;
	}

	public void setLeave(LeaveVo leave) {
		this.leave = leave;
	}

	public List<LeaveVo> getVoLst() {
		return voLst;
	}

	public void setVoLst(List<LeaveVo> voLst) {
		this.voLst = voLst;
	}

	public List<LeaveVo> getDealWithLst() {
		return dealWithLst;
	}

	public void setDealWithLst(List<LeaveVo> dealWithLst) {
		this.dealWithLst = dealWithLst;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * 登录
	 */
	public String login(){
		
		boolean checkUser = identityService.checkPassword(email, password);
		if (checkUser) {
			User user = identityService.createUserQuery().userId(email).singleResult();
			getRequest().getSession().setAttribute("loginUser", user);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 请假
	 */
	public String submitLeave(){
		
		leave.setUserId(((User)(getRequest().getSession().getAttribute("loginUser"))).getId());
		try {
			baseService.saveLeave(leave);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 获取未完成、待处理、已完成的流程
	 */
	public String getUnfinished(){
		
		voLst = baseService.getUnfinished((User)(getRequest().getSession().getAttribute("loginUser")));
		dealWithLst = baseService.getDealWith((User)(getRequest().getSession().getAttribute("loginUser")));
		
		return SUCCESS;
	}
	
	/**
	 * 签收
	 */
    public String sign(){
		
    	taskService.claim((String)params.get("taskId"), ((User)(getRequest().getSession().getAttribute("loginUser"))).getId());
		
    	return SUCCESS;
	}
    
    /**
	 * 审批、销假
	 */
    public String dealWith(){
		
    	taskService.complete((String)params.get("taskId"), params);
    	
		return SUCCESS;
	}
}
