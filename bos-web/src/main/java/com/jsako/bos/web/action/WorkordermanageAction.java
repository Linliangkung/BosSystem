package com.jsako.bos.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Workordermanage;
import com.jsako.bos.service.IWorkordermanageService;
import com.jsako.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	
	@Autowired
	private IWorkordermanageService workordermanageService;
	
	public String add() throws IOException{
		String data="1";
		try{
			workordermanageService.save(getModel());
		}catch(Exception e){
			data="0";
			e.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		return NONE;
	}
}
