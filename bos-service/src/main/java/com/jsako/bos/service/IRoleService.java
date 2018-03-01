package com.jsako.bos.service;

import java.util.List;

import com.jsako.bos.domain.Role;
import com.jsako.bos.utils.PageBean;

public interface IRoleService {
	
	/**
	 * 添加角色
	 * @param role
	 */
	void add(Role role);
	
	/**
	 * 分页查询角色
	 * @param pageBean 
	 */
	void pageQuery(PageBean pageBean);
	
	/**
	 * 查询全部角色
	 * @return
	 */
	List<Role> findAll();

}
