package com.jsako.bos.dao;

import java.util.List;

import com.jsako.bos.dao.base.IBaseDao;
import com.jsako.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region>{
	
	/**
	 * 根据q关键字查找区域
	 * @param q 关键字
	 * @return 关键字对应的区域封装的list集合
	 */
	List<Region> findListByQ(String q);

}
