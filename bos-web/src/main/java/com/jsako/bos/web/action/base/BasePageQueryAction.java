package com.jsako.bos.web.action.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.jsako.bos.utils.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BasePageQueryAction<T> extends BaseAction<T> {
	protected PageBean pageBean = new PageBean();
	protected DetachedCriteria detachedCriteria;
	
	public BasePageQueryAction(){
		detachedCriteria=DetachedCriteria.forClass(clazz);
		pageBean.setDetachedCriteria(detachedCriteria);
	}
	
	public void setPage(Integer page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(Integer rows) {
		pageBean.setPageSize(rows);;
	}
	
	/**
	 * 将pagebean对象转成json，并相应给客户端
	 * @throws IOException
	 */
	public void java2Json(Object o,String[] exclueds) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(exclueds);
		String data = JSONObject.fromObject(o, jsonConfig).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
	}
	
	/**
	 * 将list对象转成json，并相应给客户端
	 * @throws IOException
	 */
	public void java2Json(List list,String[] exclueds) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(exclueds);
		String data = JSONArray.fromObject(list, jsonConfig).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
	}
	
}
