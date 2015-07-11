package com.hed.myapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hed.myapp.dao.BaseDao;
import com.hed.myapp.entity.Leave;
import com.hed.myapp.service.BaseService;
import com.hed.myapp.vo.LeaveVo;

@Service("baseService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BaseServiceImpl implements BaseService {

	@Autowired
	private BaseDao baseDao;

	@Resource(name="identityService")
	private IdentityService identityService;
	
	@Resource(name="runtimeService")
	private RuntimeService runtimeService;
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean saveLeave(LeaveVo leaveVo) throws ParseException {
		if (leaveVo != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Leave leave = new Leave();
			leave.setUserId(leaveVo.getUserId());
			leave.setStartTime(sdf.parse(leaveVo.getStartTime()));
			leave.setEndTime(sdf.parse(leaveVo.getEndTime()));
			leave.setLeaveType(leaveVo.getLeaveType());
			leave.setReason(leaveVo.getReason());
			leave.setApplyTime(sdf.parse(leaveVo.getStartTime()));
			leave.setRealityStartTime(sdf.parse(leaveVo.getStartTime()));
			leave.setRealityEndTime(sdf.parse(leaveVo.getEndTime()));
			
			String uuid = baseDao.saveLeave(leave);
			if (StringUtils.isNotBlank(uuid)) {
				// ��������������Աid��������Զ���id���浽activiti:initiator��
				identityService.setAuthenticatedUserId(leaveVo.getUserId());
				// ��������
				Map<String, Object> variables = new HashMap<String, Object>();
				ProcessInstance processInstance = 
						runtimeService.startProcessInstanceByKey("myProcess", uuid, variables);
				// ��ȡ����ʵ��id
				String processInstanceId = processInstance.getId();
				// ���������Ϣ��
				if (StringUtils.isNotBlank(processInstanceId)) {
					baseDao.updateLeave(uuid, processInstanceId);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ��ȡδ�������ʵ��
	 */
	@Override
	public List<LeaveVo> getUnfinished(User user){
		ProcessInstanceQuery query = 
				runtimeService.createProcessInstanceQuery().processDefinitionKey("myProcess")
				.active().orderByProcessInstanceId().desc();
		List<ProcessInstance> list = query.list();
		
		List<LeaveVo> voLst = new ArrayList<LeaveVo>();
		LeaveVo vo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!CollectionUtils.isEmpty(list)) {
			for (ProcessInstance instance : list) {
				Leave leave = baseDao.queryLeaveByUsrIdAndInsId(user.getId(), instance.getId());
				if (leave != null) {
					String businessKey = instance.getProcessInstanceId();
					TaskQuery taskQuery = taskService.createTaskQuery().processInstanceId(businessKey);
					List<Task> tasks = taskQuery.list();
					
					if (!CollectionUtils.isEmpty(tasks)) {
						vo = new LeaveVo();
						vo.setId(leave.getId());
						vo.setStartTime(sdf.format(leave.getStartTime()));
						vo.setEndTime(sdf.format(leave.getEndTime()));
						vo.setLeaveType(leave.getLeaveType());
						vo.setReason(leave.getReason());
						vo.setUserName(user.getFirstName() + user.getLastName());
						vo.setDisposeUser(tasks.get(0).getAssignee());
						vo.setCurrNode(tasks.get(0).getName());
						vo.setProcessInstanceId(tasks.get(0).getProcessInstanceId());
						
						voLst.add(vo);
					}
				}
			}
		}
		return voLst;
	}
	
	/**
	 * ��ȡ����������ʵ��
	 */
	@Override
	public List<LeaveVo> getDealWith(User user){
		
		List<LeaveVo> voLst = new ArrayList<LeaveVo>();
		LeaveVo vo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String usrId = user.getId();
		// ���ݵ�ǰ�û���ID��ѯ����ǩ�յ�����
		List<Task> allTasks = new ArrayList<Task>();
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey("myProcess")
				.taskAssignee(usrId).orderByTaskId().desc().orderByTaskCreateTime().desc();
		
		List<Task> todoTasks = taskQuery.list();
		allTasks.addAll(todoTasks);
		
		// ���ݵ�ǰ�û���ID��ѯ��δǩ�յ�����
		taskQuery = taskService.createTaskQuery().processDefinitionKey("myProcess")
				.taskCandidateUser(usrId).orderByTaskId().desc().orderByTaskCreateTime().desc();
		//taskQuery = taskService.createTaskQuery().processDefinitionKey("myProcess")
		//		.taskCandidateGroup("deptLeader").orderByTaskId().desc().orderByTaskCreateTime().desc();
		List<Task> unsignedTasks = taskQuery.list();
		allTasks.addAll(unsignedTasks);
		
		for (Task task : allTasks) {
			// ��ȡ����ʵ��ID
			String processInstanceId = task.getProcessInstanceId();
			// ��ȡ����
			ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
					.active().singleResult();
			// ������ʵ���л�ȡ��ٶ�Ӧ��ID��businessKey��
			String businessKey = instance.getBusinessKey();
			Leave leave = baseDao.getLeaveById(businessKey);
			
			if (leave != null) {
				vo = new LeaveVo();
				vo.setId(leave.getId());
				vo.setStartTime(sdf.format(leave.getStartTime()));
				vo.setEndTime(sdf.format(leave.getEndTime()));
				vo.setLeaveType(leave.getLeaveType());
				vo.setReason(leave.getReason());
				vo.setUserName(user.getFirstName() + user.getLastName());
				vo.setDisposeUser(task.getAssignee());
				vo.setCurrNode(task.getName());
				vo.setProcessInstanceId(task.getProcessInstanceId());
				
				vo.setTaskId(task.getId());
				vo.setInsId(instance.getId());
				//vo.setDefId(instance.getProcessDefinitionId());
				
				System.out.println(task.getProcessInstanceId() + ":" + instance.getId());
				System.out.println(task.getProcessDefinitionId() + ":" + instance.getProcessDefinitionId());
				
				voLst.add(vo);
			}
		}
		
		return voLst;
	}
}
