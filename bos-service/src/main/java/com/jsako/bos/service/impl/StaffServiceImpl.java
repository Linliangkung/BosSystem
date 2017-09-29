package com.jsako.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IStaffDao;
import com.jsako.bos.domain.Staff;
import com.jsako.bos.service.IStaffService;
import com.jsako.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;

	@Override
	public void add(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				staffDao.executeUpdate("staff.updateDeltag", "1", id);
			}
		}
	}

	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public List<Staff> findListNotDeleteByQ(String q) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Staff.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		if(StringUtils.isNotBlank(q)){
			detachedCriteria.add(Restrictions.like("name", "%"+q+"%"));
		}
		return staffDao.findByCriteria(detachedCriteria);
	}


}
