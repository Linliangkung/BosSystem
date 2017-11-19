package com.jsako.bos.service;

import com.jsako.bos.domain.Decidedzone;
import com.jsako.bos.utils.PageBean;

public interface IDecidedzoneService {
	
	/**
	 * 保存定区
	 * @param decidedzone 定区封装的bean
	 */
	void save(Decidedzone decidedzone);
	/**
	 * 定区分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);
	
}
