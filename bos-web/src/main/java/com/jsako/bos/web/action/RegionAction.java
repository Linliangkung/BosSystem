package com.jsako.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jsako.bos.domain.Region;
import com.jsako.bos.service.IRegionService;
import com.jsako.bos.utils.PinYin4jUtils;
import com.jsako.bos.web.action.base.BasePageQueryAction;


@Controller
@Scope("prototype")
public class RegionAction extends BasePageQueryAction<Region> {

	@Autowired
	private IRegionService regionService;

	private File regionFile;
	
	private String q;
	
	public String importXls() {
		String flag;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
			HSSFSheet sheet = workbook.getSheetAt(0);
			List<Region> regions = new ArrayList<Region>();
			Region region = null;
			for (Row rows : sheet) {
				if (rows.getRowNum() == 0) {
					// 跳过标题行
					continue;
				}
				region = new Region();
				String id = rows.getCell(0).getStringCellValue();
				String province = rows.getCell(1).getStringCellValue();
				String city = rows.getCell(2).getStringCellValue();
				String district = rows.getCell(3).getStringCellValue();
				String postcode = rows.getCell(4).getStringCellValue();
				// 简称shortcode
				String info = province.substring(0, province.length() - 1) + city.substring(0, city.length() - 1)
						+ district.substring(0, district.length() - 1);
				String shortcode = StringUtils.join(PinYin4jUtils.getHeadByString(info));
				// 城市编码 citycode
				String citycode = PinYin4jUtils.hanziToPinyin(city.substring(0, city.length() - 1), "");

				region.setId(id);
				region.setProvince(province);
				region.setCity(city);
				region.setDistrict(district);
				region.setPostcode(postcode);
				region.setCitycode(citycode);
				region.setShortcode(shortcode);
				regions.add(region);
			}
			regionService.saveBatch(regions);
			// 代表导入成功
			flag = "1";
		} catch (Exception e) {
			e.printStackTrace();
			// 代表导入失败
			flag = "0";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 区域分页查询
	 * 
	 * @return
	 */
	public String pageQuery() throws IOException{
		regionService.pageQuery(pageBean);
		//需要排除subareas的原因是避免死循环:因为Subarea中引用了Region,Region中引用了Subarea
		java2Json(pageBean,new String[] { "currentPage", "pageSize", "detachedCriteria","subareas","name"});
		return NONE;
	}

	public String listajax() throws IOException{
		List<Region> regions=null;//
		if(StringUtils.isNotBlank(q)){
			//说明需要根据q关键字来获取区域
			regions=regionService.findListByQ(q);
		}else{
			//查询所有区域
			regions=regionService.findAll();
		}
		java2Json(regions,new String[]{"province","city","district","postcode","shortcode","citycode","subareas"});
		return NONE;
	}
	
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public void setQ(String q) {
		this.q = q;
	}
	

}
