package com.jsako.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Function;
import com.jsako.bos.domain.Role;
import com.jsako.bos.service.IRoleService;
import com.jsako.bos.web.action.base.BasePageQueryAction;


@Controller
@Scope("prototype")
public class RoleAction extends BasePageQueryAction<Role> {
	
	@Autowired
	private IRoleService roleService;
	
	private String functionIds;
	
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	public String add(){
		Role role = getModel();
		if(StringUtils.isNotBlank(functionIds))
		{
			String[] functionIdsArr=functionIds.split(",");
			for (String functionId : functionIdsArr) {
				Function function=new Function();
				function.setId(functionId);
				role.getFunctions().add(function);
			}
		}
		roleService.add(role);
		return LIST;
	}
	
	public String pageQuery() throws IOException{
		roleService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"functions","users"});
		return NONE;
	}
	
	public String listajax() throws IOException{
		List<Role> roles=roleService.findAll();
		java2Json(roles, new String[]{"functions","users","description","code"});
		return NONE;
	}
	
	
}
