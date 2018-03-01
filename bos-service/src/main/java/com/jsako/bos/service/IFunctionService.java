package com.jsako.bos.service;

import java.util.List;

import com.jsako.bos.domain.Function;
import com.jsako.bos.utils.PageBean;

public interface IFunctionService {
	
	/**
	 * 权限的分页查询
	 * @param pageBean
	 * @return
	 */
	void pageQuery(PageBean pageBean);
	
	/**
	 * 查询所有权限数据
	 * @return
	 */
	List<Function> findAll();
	
	/**
	 * 添加权限
	 * @param model 前端提交的权限数据
	 */
	void add(Function model);
	
	/**
	 * 查询所有顶级权限，即pid 为null
	 * @return
	 */
	List<Function> findPidIsNull();
	
	/**
	 * 根据用户id查询所有需要显示到菜单的权限
	 * @param id
	 * @return
	 */
	List<Function> findFunctionMenuByUserId(Integer id);

}
