package com.qa.darksky.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.darksky.util.ElementUtil;
import com.qa.darksky.util.JavaScriptUtil;

public class HomePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	// Locators
	By today = By.xpath("//span[contains(text(),'Today')]");
	By lowest = By.xpath("//body/div[@id='week']/div[2]/div[1]/div[2]/div[1]/span[1]/span[1]");
    By highest = By.xpath("//body/div[@id='week']/div[2]/div[1]/div[2]/div[1]/span[3]/span[1]");
	
	// Methods
	public String getPageTitle() {
		return elementUtil.doGetPageTitle();
	}
	
	public void goToToday() {
		javaScriptUtil.specificScrollPageDown();
		elementUtil.waitForElementClickable(today);
		WebElement element = driver.findElement(today);
		javaScriptUtil.clickElementByJS(element);
	}
	
	public void getHighestAndLowestTemps() {
		goToToday();
		List<WebElement> list = driver.findElements(By.className("temp"));
		for(int i = 0; i < list.size(); i++) {
			String temps = list.get(i).getText();
			if(!temps.isEmpty()) {
				System.out.println(temps);
			}
		}
	}
	
	public void verifyTemps() {
		String lowestTemp = elementUtil.doGetText(lowest);
		String highestTemp = elementUtil.doGetText(highest);
		System.out.println("Lowest temperature is : " + lowestTemp);
		System.out.println("Highest temperature is : " + highestTemp);
	}

}
