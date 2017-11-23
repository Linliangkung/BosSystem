package com.jsako.bos.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Noticebill;
import com.jsako.bos.service.INoticebillService;
import com.jsako.bos.web.action.base.BaseAction;
import com.jsako.crmbos.service.Customer;
import com.jsako.crmbos.service.impl.ICustomerService;

import net.sf.json.JSONObject;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private INoticebillService noticebillService;
	
	public String findCustomerByTelephone() throws IOException{
		Customer customer = customerService.findCustomerByTelephone(getModel().getTelephone());
		String data = JSONObject.fromObject(customer).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		return NONE;
	}
	
	public String add(){
		noticebillService.save(getModel());
		return "toAdd";
	}
	
	
}
