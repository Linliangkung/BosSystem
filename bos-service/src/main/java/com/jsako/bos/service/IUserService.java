package com.jsako.bos.service;

import java.util.List;

import com.jsako.bos.domain.User;
import com.jsako.bos.utils.PageBean;

public interface IUserService {
	/**
	 * 用户登录
	 * @param model 用户bean
	 * @return 
	 */
	User login(User model);
	/**
	 * 根据用户id获得用户
	 * @param id 用户id 
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 修改密码
	 * @param id 用户id
	 * @param newPassword 新密码
	 */
	void editPassword(Integer id, String newPassword);
	/**
	 * 添加用户
	 * @param user
	 */
	void add(User user);
	/**
	 * 用户分页查询
	 * @param pageBean
	 * @return
	 */
	void pageQuery(PageBean pageBean);

}
