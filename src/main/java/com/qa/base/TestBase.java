package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;



public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;  
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	
	public TestBase() 
	{
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		         } catch (IOException e) {
			e.printStackTrace();
		        }
	 }
	                 
	
	public static void initialization() throws InterruptedException 
	{
		
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/test/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("start-maximized"); // open Browser in maximized mode
			
			 options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--dns-prefetch-disable");
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox"); 
			//options.addArguments("headless"); 
			options.addArguments("enable-automation");
			options.addArguments("--disable-browser-side-navigation");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			//options.addArguments("--disable-features=VizDisplayCompositor");
			driver = new ChromeDriver(options);
			
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--no-sandbox"); 
			//options.setExperimentalOption("useAutomationExtension", false);
			 //driver = new ChromeDriver(options);
		}
		else if(browserName.equals("FF"))
		{
			//System.setProperty("webdriver.gecko.driver", "C:\\JAYA\\drivers\\geckodriver.exe");	
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");	
			//driver = new FirefoxDriver(); 
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("browser.download.folderList",1);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
			driver = new FirefoxDriver(options);
		}
		try {
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		
		driver.manage().window().maximize();
 		driver.manage().deleteAllCookies();
 		driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
 		driver.manage().timeouts().setScriptTimeout(3L, TimeUnit.SECONDS);
 		//driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("URL"));	
		
		
		
		while(!driver.getTitle().equals("Computers database")) {
			driver.getTitle();
		}
		}
		catch (WebDriverException e) { //this exception is thrown when Chrome becomes unreachable
		    e.printStackTrace();
		}
		finally {
		    if (driver ==null)
		        driver.quit();
		}
	}
	
	
	

}
