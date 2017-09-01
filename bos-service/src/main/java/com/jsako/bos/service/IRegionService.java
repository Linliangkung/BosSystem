package com.jsako.bos.service;

import java.util.List;

import com.jsako.bos.domain.Region;
import com.jsako.bos.utils.PageBean;

public interface IRegionService {
	/**
	 * 批量插入区域数据
	 * @param regions
	 */
	void saveBatch(List<Region> regions);
	/**
	 * 区域分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
	/**
	 * 查询所有区域
	 * @return 所有区域封装的list集合
	 */
	List<Region> findAll();
	/**
	 * 根据q关键字查找区域
	 * @param q 关键字
	 * @return 关键字对应的区域封装的list集合
	 */
	List<Region> findListByQ(String q);
	

}
