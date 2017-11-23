package com.jsako.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IWorkordermanageDao;

import com.jsako.bos.domain.Workordermanage;
import com.jsako.bos.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {
	@Autowired
	private IWorkordermanageDao workordermanageDao;
	
	@Override
	public void save(Workordermanage workordermanage) {
		
		workordermanageDao.save(workordermanage);
		
	}

}
