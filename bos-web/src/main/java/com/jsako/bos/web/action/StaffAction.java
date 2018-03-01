package com.jsako.bos.web.action;



import java.io.IOException;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Staff;
import com.jsako.bos.service.IStaffService;
import com.jsako.bos.web.action.base.BasePageQueryAction;


@Controller
@Scope("prototype")
public class StaffAction extends BasePageQueryAction<Staff>{
	@Autowired
	private IStaffService staffService;
	
	private String ids;
	
	private String q;
	
	public String add(){
		staffService.add(getModel());
		return LIST;
	}
	
	@RequiresPermissions(value={"staff.delete"})
	public String deleteBatch(){
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	@RequiresPermissions(value={"staff.edit"})
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
	
	public String pageQuery() throws IOException{
		staffService.pageQuery(pageBean);
		java2Json(pageBean,new String[] { "currentPage", "pageSize", "detachedCriteria","decidedzones" });
		return NONE;
	}
	
	public String listajax() throws IOException{
		List<Staff> staffs=staffService.findListNotDeleteByQ(q);
		java2Json(staffs,new String[]{"telephone","haspda","deltag","station","standard","decidedzones"});
		return NONE;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setQ(String q) {
		this.q = q;
	}
}
