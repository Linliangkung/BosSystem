package com.jsako.bos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IFunctionDao;
import com.jsako.bos.domain.Function;
import com.jsako.bos.service.IFunctionService;
import com.jsako.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
	@Autowired
	private IFunctionDao functionDao;
	
	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findAll() {
		return functionDao.findAll();
	}

	@Override
	public void add(Function model) {
		Function parentFunction = model.getParentFunction();
		if(parentFunction!=null&&parentFunction.getId().equals("")){
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}

	@Override
	public List<Function> findPidIsNull() {
		return functionDao.findPidIsNull();
	}

	@Override
	public List<Function> findFunctionMenuByUserId(Integer id) {
		return functionDao.findFunctionMenuByUserId(id);
	}

}
