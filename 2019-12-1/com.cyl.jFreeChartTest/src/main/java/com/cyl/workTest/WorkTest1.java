package com.cyl.workTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkTest1 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		DeepCopyTest  dct = new DeepCopyTest("erdaye");
		DeepCopyTest  dctcopy = (DeepCopyTest)dct.clone();
		
		dctcopy.setTemp1("sandaye");
		System.out.println(dct.getTemp1());
		System.out.println(dctcopy.getTemp1());
		/*long currentTimeMillis = System.currentTimeMillis();
		XSSFWorkbook hssfWorkBook = new XSSFWorkbook(new FileInputStream("C:\\work\\workSystemFile\\temp1.xlsx"));
		int numberOfSheets = hssfWorkBook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			XSSFSheet sheetObj = hssfWorkBook.getSheetAt(i);
			int lastRowNum = sheetObj.getLastRowNum();
			System.out.println("--------"+lastRowNum+"-------");
			for (int j = 0; j <= lastRowNum; j++) {
				XSSFRow row = sheetObj.getRow(j);
				for (int k = 0; k <= row.getLastCellNum(); k++) {
					if(null == row || null == row.getCell(k).getStringCellValue())continue;
					if("清算交收账户".equals(row.getCell(k).getStringCellValue())){
						XSSFCell cell = row.getCell(k);
						
						System.out.println("-----------:"+cell.getRowIndex() + "---------------:" + cell.getColumnIndex());
					}
				}
			}
		}
		
		
		hssfWorkBook.write(new FileOutputStream("C:\\work\\workSystemFile\\temp2"+System.currentTimeMillis()+".xlsx"));
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("总耗时：" + (double)(currentTimeMillis2 - currentTimeMillis)/1000 + " s");
		
		Map<String,Object> value = new HashMap<String, Object>();*/
		
		
		
	}
}
