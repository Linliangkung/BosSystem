package com.jsako.bos.dao;


import java.util.List;

import com.jsako.bos.dao.base.IBaseDao;
import com.jsako.bos.domain.Function;

public interface IFunctionDao extends IBaseDao<Function>{
	/**
	 * 查询所有顶级权限，即pid 为null
	 * @return
	 */
	List<Function> findPidIsNull();
	
	/**
	 * 根据用户id获取当前用户的所有权限
	 * @param id
	 * @return
	 */
	List<Function> getFunctionsByUserId(Integer userId);
	
	/**
	 * 根据用户id查询所有需要显示到菜单的权限
	 * @param id
	 * @return
	 */
	List<Function> findFunctionMenuByUserId(Integer id);
}
