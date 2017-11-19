package com.jsako.bos.web.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Subarea;
import com.jsako.bos.service.ISubareaService;
import com.jsako.bos.utils.FileUtils;
import com.jsako.bos.web.action.base.BasePageQueryAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class SubareaAction extends BasePageQueryAction<Subarea> {

	@Autowired
	private ISubareaService subareaService;

	private String decidedzoneId;
	
	public String add() {
		subareaService.add(getModel());
		return LIST;
	}

	public String pageQuery() throws IOException {
		Subarea subarea = getModel();
		String addresskey = subarea.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)) {
			// 根据关键字进行模糊查询
			detachedCriteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
		}
		if (subarea.getRegion() != null) {
			String province = subarea.getRegion().getProvince();
			String city = subarea.getRegion().getCity();
			String district = subarea.getRegion().getDistrict();
			// 为区域创建一个别名r
			detachedCriteria.createAlias("region", "r");
			if (StringUtils.isNotBlank(province)) {
				// 根据省份进行模糊查询
				detachedCriteria.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if (StringUtils.isNotBlank(city)) {
				// 根据城市进行模糊查询
				detachedCriteria.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if (StringUtils.isNotBlank(district)) {
				// 根据区进行模糊查询
				detachedCriteria.add(Restrictions.like("r.district", "%" + district + "%"));
			}
		}
		subareaService.pageQuery(pageBean);
		java2Json(pageBean, new String[] { "currentPage", "pageSize", "detachedCriteria", "subareas", "decidedzone",
				"name", "postcode", "citycode", "shortcode" });
		return NONE;
	}

	public String exportXls() throws IOException {
		// 查询所有分区数据
		List<Subarea> subareas = subareaService.findAll();
		// 使用poi生成excel
		// 在内存中创建一个excel
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// 创建一个标签页
		HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
		// 创建一个标题行
		HSSFRow titleRow = sheet.createRow(0);
		// 创建标题行单元格
		titleRow.createCell(0).setCellValue("分区编号");
		titleRow.createCell(1).setCellValue("关键字");
		titleRow.createCell(2).setCellValue("起始号");
		titleRow.createCell(3).setCellValue("终止号");
		titleRow.createCell(4).setCellValue("位置信息");
		titleRow.createCell(5).setCellValue("省市区");
		// 遍历分区数据,写入excel中
		HSSFRow contentRow = null;
		for (Subarea subarea : subareas) {
			contentRow = sheet.createRow(sheet.getLastRowNum() + 1);
			contentRow.createCell(0).setCellValue(subarea.getId());
			contentRow.createCell(1).setCellValue(subarea.getAddresskey());
			contentRow.createCell(2).setCellValue(subarea.getStartnum());
			contentRow.createCell(3).setCellValue(subarea.getEndnum());
			contentRow.createCell(4).setCellValue(subarea.getPosition());
			contentRow.createCell(5).setCellValue(subarea.getRegion().getName());
		}
		// 使用输出流进行文件下载
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletOutputStream outputStream = response.getOutputStream();
		String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		response.setHeader("content-disposition", "attachment;filename=" + FileUtils.encodeDownloadFilename(filename,
				ServletActionContext.getRequest().getHeader("User-Agent")));
		response.setContentType(contentType);
		hssfWorkbook.write(outputStream);
		return NONE;
	}
	
	public String findSubareaListByDecidedzoneId() throws IOException{
		List<Subarea> subareaList=subareaService.findSubareaListByDecidedzoneId(decidedzoneId);
		java2Json(subareaList, new String[]{"subareas","postcode","shortcode","citycode","decidedzone","name"});
		return NONE;
	}
	
	public String listajax() throws IOException{
		List<Subarea> subareas=subareaService.findListNotAssociation();
		java2Json(subareas, new String[]{"decidedzone","region","startnum","endnum","single"});
		return NONE;
	}
	
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

}
