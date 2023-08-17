package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Utilities {
	
	public static final int  IMPLICIT_WAIT_TIME=15;
	public static final int PAGE_LOAD_TIME=15;
	
	
	
	public static String generateEmailWithTimestamp() {
		Date date=new Date();
		String timeStamp=date.toString().replace(":", "_").replace(" ", "_");
		return "sonam.sharmabgh"+timeStamp+"@gmail.com";
		
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
		File excelFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet=workbook.getSheet(sheetName);
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			
			XSSFRow row=sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++) {
				
				XSSFCell cell=row.getCell(j);
			
				switch (cell.getCellType()) {
				case STRING:
                    data[i][j]=cell.getStringCellValue();
                    break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());//give double value 0.00
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break; 
				default:
					break;
				}
				
			}
		}
		
		return data;
		
}
	
	public static String captureScreenshot(WebDriver driver,String testName) {
		//TakesScreenshot takesScreenshot = (TakesScreenshot) driver; 
		
				File srcScreenshotFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String destinationScreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
				File destinationScreenshotFile=new File(destinationScreenshotPath);
				
				try {
					
			        FileHandler.copy(srcScreenshotFile, destinationScreenshotFile);
				
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return destinationScreenshotPath;
				
	}


}
