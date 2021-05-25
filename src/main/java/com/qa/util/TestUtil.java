package com.qa.util;
import java.io.File;
import org.apache.commons.io.FileUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.openqa.selenium.Keys.TAB;


import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;



import com.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static int PAGE_LOAD_TIMEOUT  = 50;
	public static long IMPLICIT_WAIT = 50;
	static final String random = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();


	/**
	 * To generate random string
	 */
	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(random.charAt(rnd.nextInt(random.length())));
		return sb.toString();
	}


		
	//Method to capture the Screen shots at the end only when testcase is failed
	public static void takeScreenshotAtEndOfTest() throws Exception {
		try {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.Dir");
				//"MyfilePATH");
		FileUtils.copyFile(scrFile, new File("/screenshots/" +currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}catch (Exception ex) {
		System.out.println(ex.getMessage());
	}}	
	//Method to capture the Screen shots
		public static void takeScreenshot(String fileName) throws IOException {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("C:/jaya/UIAutomationAssignment/Screenshots_KlipClear/"+fileName+".jpg"));
		}
		
	

	

	public static void clickElement(WebElement element) throws InterruptedException {
		

        int seconds = PAGE_LOAD_TIMEOUT;
        long time = 1000 * seconds;
        boolean timeout = false;
        while (!timeout && time > 0) {
            try {
            	element.click();
            	
                timeout = true;
                Thread.sleep(100);
            } catch (Exception e) {
                timeout = false;
                Thread.sleep(100);
                time = time - 100;
            }
        }
	}


	public static void selectFromDropdownList(WebElement element, String value) throws InterruptedException {
		if(value!=null){
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(value);
			element.click();
		}
	}


	 public static void enterText(WebElement element, String value) throws InterruptedException
	 {
	 element.click();
	 String str=element.getText();
	 if(str!=null ) 
	 {
     element.clear();
	 }
		 if (value != null) {
			 element.sendKeys(value);
			 element.sendKeys(TAB);
		 }
	 }


	public static int GetRowNumberForSpecificTest(String SheetName, String stringToCompare)throws Exception{
		FileInputStream fileInputStream = null;
		int rowNum=0;
		try{
			fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/TestData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet worksheet = workbook.getSheet(SheetName);
			Iterator<Row> rowIterator = worksheet.iterator();
			int rowNumber=0;
			while (rowIterator.hasNext()) {
				rowNumber++;
				Row row = rowIterator.next();
				if (row.getCell(0).getStringCellValue().equalsIgnoreCase(stringToCompare)) {
					rowNum=rowNumber;
					break;
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			// throw new Exception("Failed to retrieve value from test data xlsx");
		}
		return rowNum;
	}


	public static String[] readExcelValues(String SheetName, String TestCaseRef) throws Exception
	{
		int rowNum = GetRowNumberForSpecificTest(SheetName, TestCaseRef);
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/TestData.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Creating a workbook
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(SheetName);
		Row row = sheet.getRow(rowNum-1);
		String[] value = new String[row.getLastCellNum()];
		for(int i = 0; i <= row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);

			if(cell!=null && cell.getCellType() != Cell.CELL_TYPE_BLANK && cell.getCellType() == Cell.CELL_TYPE_STRING) {
				value[i]=cell.getStringCellValue();
			}else if(cell!=null && cell.getCellType() != Cell.CELL_TYPE_BLANK && cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
				value[i]=String.valueOf((int) cell.getNumericCellValue());
			}


		}
		String[]newValue=Arrays.copyOfRange(value, 1, value.length);
		return newValue;
	}
}
	
	

