package com.jsako.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jsako.bos.dao.base.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	// 用于接收运行时泛型类型
	private Class clazz;

	public BaseDaoImpl() {
		super();
		// 获得当前类型的带有泛型的父类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得当前类的泛型类型
		clazz = (Class) type.getActualTypeArguments()[0];
	}
	
	//为HibernateDaoSupport注入SessionFactory(会话工厂),即用来调用HibernateDaoSupport的setSessionFactory的方法
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		String hql="FROM "+clazz.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session currentSession = this.getSessionFactory().getCurrentSession();
		Query query = currentSession.getNamedQuery(queryName);
		int i=0;
		//为hql的?赋值
		for(Object object:objects){
			query.setParameter(i++, object);
		}
		query.executeUpdate();
	}


}
