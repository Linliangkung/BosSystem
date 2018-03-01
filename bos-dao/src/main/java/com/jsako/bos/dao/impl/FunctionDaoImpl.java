package com.jsako.bos.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsako.bos.dao.IFunctionDao;
import com.jsako.bos.dao.base.impl.BaseDaoImpl;
import com.jsako.bos.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findPidIsNull() {
		String hql="FROM Function f where f.parentFunction.id is NULL";
		return (List<Function>) getHibernateTemplate().find(hql);
	}

	@Override
	public List<Function> getFunctionsByUserId(Integer userId) {
		String hql="SELECT DISTINCT f FROM Function f INNER JOIN f.roles r INNER JOIN r.users u where u.id=? ";
		return (List<Function>) getHibernateTemplate().find(hql, userId);
	}

	@Override
	public List<Function> findFunctionMenuByUserId(Integer id) {
		String hql="SELECT DISTINCT f FROM Function f INNER JOIN f.roles r INNER JOIN r.users u where u.id=? and f.generatemenu=1 order by f.zindex asc ";
		return (List<Function>) getHibernateTemplate().find(hql, id);
	}


}
