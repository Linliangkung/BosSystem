package com.jsako.bos.service.impl;

import java.sql.Timestamp;

import org.apache.commons.beanutils.WrapDynaBean;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsako.bos.dao.IDecidedzoneDao;
import com.jsako.bos.dao.INoticebillDao;
import com.jsako.bos.dao.IWorkbillDao;
import com.jsako.bos.domain.Noticebill;
import com.jsako.bos.domain.Staff;
import com.jsako.bos.domain.User;
import com.jsako.bos.domain.Workbill;
import com.jsako.bos.service.INoticebillService;
import com.jsako.bos.utils.BOSUtils;
import com.jsako.crmbos.service.impl.ICustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {
	@Autowired
	private INoticebillDao noticebillDao;
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	
	@Autowired
	private IWorkbillDao workbillDao;
	
	@Autowired
	private ICustomerService customerService;
	
	@Override
	public void save(Noticebill noticebill) {
		//设置处理人,即当前登录系统的用户
		User loginUser = BOSUtils.getLoginUser();
		noticebill.setUser(loginUser);
		//添加保存一个业务通知单
		noticebillDao.save(noticebill);
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(noticebill.getPickaddress());
		if(decidedzoneId!=null){
			//自动分单
			noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			//业务通知单关联取派员对象
			Staff staff = decidedzoneDao.findById(decidedzoneId).getStaff();
			noticebill.setStaff(staff);
			//为取派员产生一个工单
			Workbill workbill=new Workbill();
			//追单次数
			workbill.setAttachbilltimes(0);
			//工单创建时间
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			//工单关联业务通知单
			workbill.setNoticebill(noticebill);
			//取件状态
			workbill.setPickstate(Workbill.PICKSTATE_NO);
			//备注信息
			workbill.setRemark(noticebill.getRemark());
			//工单关联取派员
			workbill.setStaff(staff);
			//工单类型
			workbill.setType(Workbill.TYPE_1);
			workbillDao.save(workbill);
		}else{
			//人工分单
			noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}

}
