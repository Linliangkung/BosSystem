package com.jsako.bos.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IRegionDao;
import com.jsako.bos.domain.Region;
import com.jsako.bos.service.IRegionService;
import com.jsako.bos.utils.PageBean;
@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	@Autowired
	private IRegionDao regionDao;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveBatch(List<Region> regions) {
		int count=1;
		for(Region region:regions){
			regionDao.saveOrUpdate(region);
			if(count%100==0){
				//批量插入，防止内存溢出
				Session currentSession = sessionFactory.getCurrentSession();
				currentSession.flush();
				currentSession.clear();
			}
			count++;	
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findListByQ(String q) {
		return regionDao.findListByQ(q);
	}
}
