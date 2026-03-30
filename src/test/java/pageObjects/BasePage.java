package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}
	
	By body = By.xpath("//body");
	
	public boolean checkDarkTheme() {
		if(driver.findElement(body).getAttribute("class").equals("dark")) {
			return true;
		}
		return false;
	}
	
	public boolean checkLightTheme() {
		if(!driver.findElement(body).getAttribute("class").equals("dark")) {
			return true;
		}
		return false;
	}
	
}
