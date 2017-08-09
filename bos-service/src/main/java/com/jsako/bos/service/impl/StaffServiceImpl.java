package com.jsako.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IStaffDao;
import com.jsako.bos.domain.Staff;
import com.jsako.bos.service.IStaffService;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	
	@Autowired
	private IStaffDao staffDao; 
	
	@Override
	public void add(Staff model) {
		staffDao.save(model);
	}
}
