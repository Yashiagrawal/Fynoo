package www.keword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import www.base.keyword.Base;

public class Keywordengine {
	
	
	public WebDriver driver;
	
	public Properties prop;
	
	public static Workbook book;
	
	public static Sheet sheet;

	public Base base;
	
	public WebElement element;
	public final String SCENARIO_SHEET_PATH ="C:\\EXcel data\\login.xlsx";

	public void startExecution(String sheetname){
		
		String locatorName = null;
		String locatorValue = null;

		FileInputStream file= null;
		
		
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetname);
		int k=0;
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			
		
			//locators
			String locatorColValue =sheet.getRow(i+1).getCell(k+1).toString().trim();//id=username
			if(!locatorColValue.equalsIgnoreCase("NA")) {
				locatorColValue.split("=")[0].trim();
				locatorName = locatorColValue.split("=")[0].trim();//id
				locatorValue = locatorColValue.split("=")[1].trim();//username
				
			}
		
		String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
		String value = sheet.getRow(i+1).getCell(k+3).toString().trim();

		
		//browser
		switch (action) {
		case "openbrowser":
			
			base = new Base();
			prop = base.init_properties();
			if(value.isEmpty() || value.equals("NA")) {
				driver =base.init_driver(prop.getProperty("browser"));
			}else {
				driver=base.init_driver(value);
			}
			
			break;
			
		case "enter_url":
			
			if(value.isEmpty() || value.equals("NA")) {
				driver.get(prop.getProperty("url"));
			}else {
				driver.get(value);
			}
			
			
		
		case "Quit":
			
			driver.quit();
			break;
			
		default:
			break;
		}
		
	try {	
		// locators
			switch (locatorName) {
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if (action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click();
				}

				locatorName = null;
				break;

			case "linkText":
				element = driver.findElement(By.linkText(locatorValue));
				element.click();

				break;

			default:
				break;
			}
	}
	catch(Exception e) {
	}
	}
		}

	}


