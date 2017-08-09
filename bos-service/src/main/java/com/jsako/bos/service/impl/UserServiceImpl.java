package com.jsako.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IUserDao;
import com.jsako.bos.domain.User;
import com.jsako.bos.service.IUserService;
import com.jsako.bos.utils.MD5Utils;


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

}
