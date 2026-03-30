package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderSection {
	
	WebDriver driver;
	
	public HeaderSection(WebDriver driver){
		this.driver=driver;
	}
	
	By headingHomePage = By.xpath("//h1[normalize-space()='Developer Dashboard']");
	By homeLink = By.xpath("//a[normalize-space()='Home']");
	By analyticsLink = By.xpath("//a[normalize-space()='Analytics']");
	By toggleButton = By.cssSelector("svg[width='28']");
	
	public boolean checkHeading() {
		if(driver.findElement(headingHomePage).getText().equalsIgnoreCase("Developer Dashboard")) {
			return true;
		}
		return false;
	}
	
	public void clickHome() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
	}
	
	public void clickAnalytics() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(analyticsLink)).click();
	}
	
	public void ThemeChange() {
		driver.findElement(toggleButton).click();
	}
	
	
}
