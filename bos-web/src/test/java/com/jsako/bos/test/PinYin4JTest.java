package com.jsako.bos.test;



import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.jsako.bos.utils.PinYin4jUtils;

public class PinYin4JTest {
	
	@Test
	public void test(){
		//广东省 广州市 白云区
		//简码
		String province="广东省";
		String city="广州市";
		String district="白云区";
		province=province.substring(0,province.length()-1);
		city=city.substring(0,city.length()-1);
		district=district.substring(0,district.length()-1);
		
		String info=province+city+district;

		String[] shortcodeArr = PinYin4jUtils.getHeadByString(info);
		String shortcode = StringUtils.join(shortcodeArr);
		System.out.println(shortcode);
		//城市编码
		String citycode = PinYin4jUtils.hanziToPinyin(city, "");
		System.out.println(citycode);
	}
	
	@Test
	public void test2(){
		
		Integer[] integers=new Integer[0];
		System.out.println(integers[0]);
	}
	
	
	
	
	
}
