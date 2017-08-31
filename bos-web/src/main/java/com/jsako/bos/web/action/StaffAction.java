package com.jsako.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Staff;
import com.jsako.bos.service.IStaffService;
import com.jsako.bos.utils.PageBean;
import com.jsako.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	@Autowired
	private IStaffService staffService;
	
	private Integer page;
	private Integer rows;
	
	private String ids;
	
	public String add(){
		staffService.add(getModel());
		return LIST;
	}
	
	public String pageQuery() throws IOException{
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(rows);
		pageBean.setCurrentPage(page);
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(detachedCriteria);
		staffService.pageQuery(pageBean);
		//使用json-lib将pageBean转换成json数据,写回给浏览器页面
		//设置那些属性不需要转成json输出
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
		String data = JSONObject.fromObject(pageBean,jsonConfig).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		return NONE;
	}
	
	public String deleteBatch(){
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	public String edit(){
		//根据id查出取派员
		Staff queryStaff=staffService.findById(getModel().getId());
		//根据页面传来的model数据覆盖查询出来的取派员信息
		queryStaff.setName(getModel().getName());
		queryStaff.setHaspda(getModel().getHaspda());
		queryStaff.setStandard(getModel().getStandard());
		queryStaff.setStation(getModel().getStation());
		queryStaff.setTelephone(getModel().getTelephone());
		staffService.update(queryStaff);
		return LIST;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
