package com.jsako.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.sql.Delete;

/**	
 * 持久层通用接口
 * @author linliangkung
 *
 */
public interface IBaseDao<T> {
	void save(T entity);
	void delete(T entity);
	void update(T entity);
	T findById(Serializable id);
	List<T> findAll();
}
