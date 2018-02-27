package com.jsako.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.ResourceTransactionManager;

import com.jsako.bos.domain.Function;
import com.jsako.bos.service.IFunctionService;
import com.jsako.bos.web.action.base.BasePageQueryAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BasePageQueryAction<Function> {
	
	@Autowired
	private IFunctionService functionService;
	
	
	public String pageQuery() throws IOException{
		functionService.pageQuery(pageBean);
		java2Json(pageBean, new String[]{"currentPage", "pageSize", "detachedCriteria","parentFunction","code","roles","children"});
		return NONE;
	}
	
	public String listajax() throws IOException{
		List<Function> functions=functionService.findAll();
		java2Json(functions, new String[]{"parentFunction","code","description","pageResource",
				"generatemenu","zindex","roles","children"});
		return NONE;
	}
	
	public String add(){
		functionService.add(getModel());
		return LIST;
	}
}
