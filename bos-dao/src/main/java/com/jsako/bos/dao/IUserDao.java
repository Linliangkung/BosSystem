package com.jsako.bos.dao;

import com.jsako.bos.dao.base.IBaseDao;
import com.jsako.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {
	
	/**
	 * 根据用户名和密码查询用户
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	User findUserByUsernameAndPassword(String username, String password);

}
