package com.jsako.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Decidedzone;
import com.jsako.bos.domain.Subarea;
import com.jsako.bos.service.IDecidedzoneService;
import com.jsako.bos.service.ISubareaService;
import com.jsako.bos.web.action.base.BasePageQueryAction;
import com.jsako.crmbos.service.Customer;
import com.jsako.crmbos.service.impl.ICustomerService;

/**
 * 定区管理
 * @author linliangkung
 *
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BasePageQueryAction<Decidedzone>{
	
	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	@Autowired
	private ICustomerService customerService;
	
	private String[] subareaid;
	
	private List<Integer> customerIds;
	

	public String add(){
		Decidedzone decidedzone = getModel();
		Subarea subarea=null;
		for(String id:subareaid){
			subarea=new Subarea();
			subarea.setId(id);
			decidedzone.getSubareas().add(subarea);
		}
		decidedzoneService.save(decidedzone);
		return LIST;
	}

	public String pageQuery() throws IOException{
		decidedzoneService.pageQuery(pageBean);
		java2Json(pageBean, new String[] { "currentPage", "pageSize", "detachedCriteria","subareas","decidedzones","haspda","deltag","standard"});
		return NONE;
	}
	
	public String findCustomerListNotAssociation() throws IOException{
		List<Customer> customers = customerService.findListNotAssociation();
		java2Json(customers, null);
		return NONE;
	}
	
	public String findCustomerByDecidedzoneId() throws IOException{
		List<Customer> customers=customerService.findListByDecidedzoneId(getModel().getId());
		java2Json(customers, null);
		return NONE;
	}
	
	public String assigncustomerstodecidedzone(){
		if(customerIds!=null){
			customerService.assigncustomerstodecidedzone(customerIds,getModel().getId());
		}else{
			customerService.clearDecidedzoneByDecidedzoneId(getModel().getId());
		}
		return  LIST;
	}
	
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	
}
