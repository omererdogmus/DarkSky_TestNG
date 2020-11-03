package com.qa.darksky.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	//WebDriver driver;
	Properties properties;
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver init_driver(String browserName) {
		highlightElement = properties.get("highlight").equals("yes") ? true : false;
		optionsManager = new OptionsManager(properties);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equals("opera")) {
			WebDriverManager.operadriver().setup();
			//driver = new OperaDriver();
			tlDriver.set(new OperaDriver());
		}
		else if(browserName.equals("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("Browser name "+ browserName + " is not found. Please pass the correct browser...!");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		
		getDriver().get(properties.getProperty("url"));
		
		return getDriver();
	
	}
	
	public Properties init_properties() {
		properties = new Properties();
		
		String path = "/Users/omererdogmus/Documents/eclipse-workspace/DarkSky_TestNG/src/main/java/com/qa/darksky/config/config.properties";
		
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Some issue with config properties... Please correct your config...!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public void quitBrowser() {
		try {
			getDriver().quit();
		} catch (Exception e) {
			System.out.println("Some exception occured while quitting browser...!");
		}
	}
	
	public void closeBrowser() {
		try {
			getDriver().close();
		} catch (Exception e) {
			System.out.println("Some exception occured while closing browser...!");
		}
	}

}
