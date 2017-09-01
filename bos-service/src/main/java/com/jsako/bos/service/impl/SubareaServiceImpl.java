package com.jsako.bos.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsako.bos.dao.ISubareaDao;
import com.jsako.bos.domain.Subarea;
import com.jsako.bos.service.ISubareaService;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	@Autowired
	private ISubareaDao subareaDao;
	
	@Override
	public void add(Subarea model) {
		subareaDao.save(model);
	}

}
