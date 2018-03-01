package com.jsako.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IUserDao;
import com.jsako.bos.domain.User;
import com.jsako.bos.service.IUserService;
import com.jsako.bos.utils.MD5Utils;
import com.jsako.bos.utils.PageBean;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public User login(User model) {
		return userDao.findUserByUsernameAndPassword(model.getUsername(),MD5Utils.md5(model.getPassword()));
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public void editPassword(Integer id, String newPassword) {
		newPassword=MD5Utils.md5(newPassword);
		userDao.executeUpdate("user.editpassword", newPassword,id);
	}

	@Override
	public void add(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDao.save(user);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);;
	}

}
