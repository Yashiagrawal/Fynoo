package www.base.keyword;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public WebDriver driver;

	public Properties prop;

	public WebDriver init_driver(String browsername) {

		if (browsername.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "C:\\Selenium Jars\\Driver\\driverchrome86\\chromedriver.exe");
			if (prop.getProperty("headless").equals("yes")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();

			} else {

				driver = new ChromeDriver();
				driver.manage().window().maximize();

				
			} // else closed

		} // if condition for browser closed

		return driver;

	}

	
	
	public Properties init_properties() {
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("C:\\EXcel data\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
}
