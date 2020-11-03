package com.qa.darksky.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.darksky.base.BasePage;
import com.qa.darksky.pages.HomePage;
import com.qa.darksky.util.Constants;

public class HomePageTest {
	
	BasePage basePage;
	WebDriver driver;
	Properties properties;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		properties = basePage.init_properties();
		String browserName = properties.getProperty("browser");
		driver = basePage.init_driver(browserName);
		homePage = new HomePage(driver);
	}
	
	@Test(priority = 1, description = "verify home page title", enabled = true)
	public void vetifyPageTitle() {
		String title = homePage.getPageTitle();
		System.out.println("Home page title is : " + title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority = 2, description = "get highest and lowest temperature", enabled = true)
	public void verifyTemps() {
		homePage.getHighestAndLowestTemps();
	}
	
	@Test(priority = 3, description = "verify highest and lowest temperature", enabled = true)
	public void verifyHighandLowTemp() {
		homePage.verifyTemps();
	}
	
	@AfterMethod
	public void tearDown() {
		basePage.quitBrowser();
	}
	
}
