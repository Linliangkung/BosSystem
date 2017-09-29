package com.jsako.bos.web.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Decidedzone;
import com.jsako.bos.domain.Subarea;
import com.jsako.bos.service.IDecidedzoneService;
import com.jsako.bos.web.action.base.BasePageQueryAction;

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
	
	private String[] subareaid;
	
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
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	
	
}
