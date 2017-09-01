package com.jsako.bos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.jsako.bos.dao.IRegionDao;
import com.jsako.bos.dao.base.impl.BaseDaoImpl;
import com.jsako.bos.domain.Region;
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		String hql="FROM Region r WHERE r.province LIKE ? OR r.city LIKE ? OR r.district LIKE ? OR r.shortcode LIKE ? OR r.citycode LIKE ?";
		return (List<Region>) getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
	}
}
