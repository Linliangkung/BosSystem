package com.jsako.bos.service;

import java.util.List;

import com.jsako.bos.domain.Subarea;
import com.jsako.bos.utils.PageBean;

public interface ISubareaService {
	
	/**
	 * 添加一个分区
	 * @param subarea
	 */
	void add(Subarea model);
	
	/**
	 * 分区分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
	/**
	 * 查询所有的分区数据
	 * @return
	 */
	List<Subarea> findAll();
	
	/**
	 * 查询所有未关联定区的分区数据
	 * @return
	 */
	List<Subarea> findListNotAssociation();

}
