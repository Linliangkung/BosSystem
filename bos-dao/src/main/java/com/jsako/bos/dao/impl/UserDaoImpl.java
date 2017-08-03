package com.jsako.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.jsako.bos.dao.IUserDao;
import com.jsako.bos.dao.base.impl.BaseDaoImpl;
import com.jsako.bos.domain.User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	
}
