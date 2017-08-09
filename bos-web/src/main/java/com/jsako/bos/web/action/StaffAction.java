package com.jsako.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Staff;
import com.jsako.bos.service.IStaffService;
import com.jsako.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	@Autowired
	private IStaffService staffService;
	
	public String add(){
		
		staffService.add(getModel());
		
		return LIST;
	}
	
}
