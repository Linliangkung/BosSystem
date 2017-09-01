package com.jsako.bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;


public class POITest {
	//使用poi解析excel文件
	@Test
	public void test() throws FileNotFoundException, IOException{
		String path="C:\\Users\\Administrator\\Desktop\\区域导入测试数据.xls";
		//包装一个excel文件
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(path)));
		//读取文件中第一个标签页(sheet1);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for(Row row:sheet){
			if(row.getRowNum()==0){
				continue;
			}
			for(Cell cell:row){
				//遍历行，获得单元格
				String stringCellValue = cell.getStringCellValue();
				System.out.print(stringCellValue+"\t");
			}
			System.out.println("");
		}
	}
	
}
