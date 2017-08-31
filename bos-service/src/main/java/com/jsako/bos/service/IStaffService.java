package com.jsako.bos.service;

import com.jsako.bos.domain.Staff;
import com.jsako.bos.utils.PageBean;

public interface IStaffService {
	/**
	 * 添加取派员
	 * @param model 取派员model对象
	 */
	void add(Staff model);
	
	/**
	 * 取派员分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
	/**
	 * 批量删除取派员,逻辑删除,将取派员的deltag改为1
	 * @param ids 取派员们的id
	 */
	void deleteBatch(String ids);
	
	/**
	 * 根据id查出取派员 
	 * @param id
	 * @return
	 */
	Staff findById(String id);
	
	/**
	 * 更新取派员
	 * @param staff
	 */
	void update(Staff staff);
	
}
