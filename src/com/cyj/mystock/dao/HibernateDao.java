package com.cyj.mystock.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public  class HibernateDao<T> {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.getSessionFactory().getCurrentSession();
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public  List<T> queryAll(String arg0,Class<?> arg1){
		return (List<T>) this.getSession().createNativeQuery(arg0, arg1).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public  List<T> queryAll(String arg0){
		return (List<T>) this.getSession().createNativeQuery(arg0).getResultList();
	}
	public BigInteger queryCount(String arg0){
		return (BigInteger)this.getSession().createNativeQuery(arg0).getSingleResult();
	}
	
	public  Object queryById(String id,Class<?> claz){
		return this.getSession().get(claz, id);
	}
	
	public void saveOrUpdate(Object arg0){
		this.getSession().saveOrUpdate(arg0);
		this.getSession().flush();
	}
	
	public void delete(String id,Class<?> claz){
		Object obj = this.getSession().get(claz, id);
		this.getSession().delete(id,obj);
		this.getSession().flush();
	}
}
