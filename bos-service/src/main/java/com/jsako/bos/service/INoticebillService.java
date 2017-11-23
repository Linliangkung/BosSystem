package com.jsako.bos.service;

import com.jsako.bos.domain.Noticebill;

public interface INoticebillService {
	/**
	 * 添加一个业务通知单
	 * @param model
	 */
	void save(Noticebill noticebill);

}
