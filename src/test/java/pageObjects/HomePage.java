package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	By yearsofExp = By.id("experience");
	By websitesDev = By.id("websites");
	By appsMade = By.id("apps");
	By measureProButton = By.xpath("//button[@type='submit']");
	By analyticsLink = By.partialLinkText("Go to Analytics");
	
	public boolean checkHomeNavigation() {
		if(driver.findElement(measureProButton).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public void enterDetails(String yoe, String webD, String appsM) {
		WebElement yoed=driver.findElement(yearsofExp);
		Select select= new Select(yoed);
		select.selectByVisibleText(yoe);
		WebElement webDev=driver.findElement(websitesDev);
		webDev.clear();
		webDev.sendKeys(webD);
		WebElement app=driver.findElement(appsMade);
		app.clear();
		app.sendKeys(appsM);
	}
	
	public void clickMesaureProfile() {
		driver.findElement(measureProButton).click();
	}
	
	public boolean checkMsrProfBtnNav() {
		return new AnalyticsPage(driver).checkAnalyticsNavigationWithSavedData();
	}
	
	public void clickAnalyticPreviewLink() {
		driver.findElement(analyticsLink).click();
	}
	
}
