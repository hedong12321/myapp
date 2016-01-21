package com.hed.myapp.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.hed.myapp.dao.BaseDao;
import com.hed.myapp.entity.Leave;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
        return sessionFactory.getCurrentSession();  
    }

	/**
	 * 保存请假
	 */
	@Override
	public String saveLeave(Leave leave) {
		return (String)getSession().save(leave);
	}
	
	/**
	 * 更新请假，以保存流程实例id
	 */
	@Override
	public boolean updateLeave(String id, String value) {
		String updateHql = " from Leave l where l.id=:idValue";
		Session session = getSession();
		Query query = session.createQuery(updateHql);
		query.setString("idValue", id);
		Leave leave = (Leave)query.uniqueResult();
		if (leave != null) {
			leave.setProcessInstanceId(value);
			
			session.update(leave);
			return true;
		}
		return false;
	}
	
	/**
	 * 获取对应用户和流程实例的请假对象
	 */
	@Override
	public Leave queryLeaveByUsrIdAndInsId(String userId, String instanceId){
		String updateHql = " from Leave l where l.userId=:userId and l.processInstanceId=:processInstanceId";
		Session session = getSession();
		Query query = session.createQuery(updateHql);
		query.setString("userId", userId);
		query.setString("processInstanceId", instanceId);
		
		return (Leave)query.uniqueResult();
	}
	
	/**
	 * 根据请假对象ID获取请假对象
	 */
	@Override
	public Leave getLeaveById(String id) {
		String leaveHql = "from Leave l where l.id=:id";
		Query query = getSession().createQuery(leaveHql);
		query.setString("id", id);
		
		return (Leave)query.uniqueResult();
	}
}
