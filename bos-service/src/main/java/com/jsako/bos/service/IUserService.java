package com.jsako.bos.service;

import com.jsako.bos.domain.User;

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

}
