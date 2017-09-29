package com.jsako.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IDecidedzoneDao;
import com.jsako.bos.domain.Decidedzone;
import com.jsako.bos.service.IDecidedzoneService;
import com.jsako.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao; 
	
	@Override
	public void save(Decidedzone decidedzone) {
		decidedzoneDao.save(decidedzone);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

}
