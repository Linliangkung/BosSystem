package com.jsako.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Subarea;
import com.jsako.bos.service.ISubareaService;
import com.jsako.bos.web.action.base.BasePageQueryAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BasePageQueryAction<Subarea> {
	
	@Autowired
	private ISubareaService subareaService;
	
	public String add(){
		subareaService.add(getModel());
		return LIST;
	}
	
}
