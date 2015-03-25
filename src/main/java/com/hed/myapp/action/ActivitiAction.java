package com.hed.myapp.action;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;

import com.hed.myapp.service.BaseService;
import com.hed.myapp.vo.LeaveVo;

public class ActivitiAction extends BaseAction {

	/**
	 * –Ú¡–ªØID
	 */
	private static final long serialVersionUID = -1077653780587008630L;

	@Resource(name="baseService")
	private BaseService baseService;
	
	@Resource(name="identityService")
	private IdentityService identityService;
	
	private String email;
	
	private String password;
	
	private LeaveVo leave;
	
	private List<LeaveVo> voLst;

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

	public String login(){
		
		boolean checkUser = identityService.checkPassword(email, password);
		if (checkUser) {
			User user = identityService.createUserQuery().userId(email).singleResult();
			getRequest().getSession().setAttribute("loginUser", user);
		}
		
		return SUCCESS;
	}
	
	public String submitLeave(){
		
		leave.setUserId(((User)(getRequest().getSession().getAttribute("loginUser"))).getId());
		try {
			baseService.saveLeave(leave);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String getUnfinished(){
		
		voLst = baseService.getUnfinished((User)(getRequest().getSession().getAttribute("loginUser")));
		
		return SUCCESS;
	}
}
