package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExtentListener;
import com.aventstack.extentreports.Status;

import testBase.BaseClass;

public class AnalyticsPage extends BaseClass {
	
	WebDriver driver;
	int scorePer=0;
	
	public AnalyticsPage(WebDriver driver) {
		this.driver=driver;	
	}
	
	By analyticsHeading1 = By.xpath("//h3[text()='Visual Analytics']");
	By analyticsHeading2 = By.xpath("//h2[text()='No Profile Data']");
	By measureProButton = By.xpath("//button[@type='submit']");
	By badge = By.id("badge");
	By score = By.id("score");
	By experience = By.id("exp-ind");
	By webDev = By.id("web-ind");
	By appsMade = By.id("app-ind");
	By suggestion = By.id("suggestion");
	By goBackBtn = By.xpath("//button[text()='Analyze Another Profile']");
	
	public boolean checkAnalyticsNavigationForFirstTime() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			String analyticstext= wait.until(ExpectedConditions.elementToBeClickable(analyticsHeading2)).getText();
			if(analyticstext.equalsIgnoreCase("No Profile Data")) {
				return true;
			}
			return false;
		}
		catch(Exception e) {
			System.out.println("Not navigated to analytics page for first time");
			return false;
		}
		
	}
	
	public boolean checkAnalyticsNavigationWithSavedData() {
		try {
			if(driver.findElement(analyticsHeading1).getText().equalsIgnoreCase("Visual Analytics")) {
				return true;
			}
			return false;
		}
		catch(Exception e) {
			System.out.println("Not navigated to analytics page with saved data");
			return false;
		}
	}
	
	public boolean checkBadge(int score) {
		String badgeText = driver.findElement(badge).getText();
		if(score<20) {
			if(badgeText.equalsIgnoreCase("Rising Talent")) {
				return true;
			}
		}
		else if(score>=20 && score<=44) {
			if(badgeText.equalsIgnoreCase("Junior Developer")) {
				return true;
			}
		}
		else if(score>=45 && score<=74) {
			if(badgeText.equalsIgnoreCase("Professional Developer")) {
				return true;
			}
		}
		else if(score>=75) {
			if(badgeText.equalsIgnoreCase("Expert Developer")) {
				return true;
			}
		}
		return false;
	}
	
	public int calculateScore(String yoe, int webD, int appsM) {
		int yearsOfExperience=0;
		if(yoe.equalsIgnoreCase("0-1 years")) {
			yearsOfExperience=0;
		}
		else if(yoe.equalsIgnoreCase("2-5 years")) {
			yearsOfExperience=4;
		}
		else if(yoe.equalsIgnoreCase("5-10 years")) {
			yearsOfExperience=8;
		}
		else if(yoe.equalsIgnoreCase("10+ years")) {
			yearsOfExperience=10;
		}
		scorePer=(yearsOfExperience*5)+(webD*4)+(appsM*8);
		
		if(scorePer>100) {
			scorePer=100;
		}
		System.out.println(yoe+"  "+webD+"  "+appsM+"   "+scorePer);
		return scorePer;
	}
	
	public boolean checkPercentScore(int percent) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String percentage= wait.until(ExpectedConditions.elementToBeClickable(score)).getText();
		int actualPer=0;
		char a=percentage.charAt(18),b,c;
		try {
			b=percentage.charAt(19);
		}
		catch(Exception e) {
			b='a';
		}
		try {
			c=percentage.charAt(20);
		}
		catch(Exception e) {
			c='a';
		}
		if(Character.isDigit(c)) {
			actualPer= Integer.parseInt(""+a+b+c);
		}
		else if(Character.isDigit(b)) {
			actualPer= Integer.parseInt(""+a+b);
		}
		else {
			actualPer= Integer.parseInt(""+a);
		}
		ExtentListener.test.get().log(Status.INFO, "Profile score displayed: " + actualPer);
		if(percent==actualPer) {
			return true;
		}
		return false;
		
	}
	
	public boolean checkresultInfo(String yoe, String webD, String appsM) {
		String expYears= (driver.findElement(experience).getText()).substring(18);
		String websitesDev= (driver.findElement(webDev).getText()).substring(20);
		String appsMd= (driver.findElement(appsMade).getText()).substring(12);
	
		if(expYears.equalsIgnoreCase(yoe)) return true;
		else if(websitesDev.equalsIgnoreCase(webD)) return true;
		else if(appsMd.equalsIgnoreCase(appsM)) return true;
		return false;
	}
	
	public boolean checkSuggestion(int perScore) {
		String sugg= driver.findElement(suggestion).getText();
		if(perScore>70) {
			if(sugg.equalsIgnoreCase("Outstanding profile! You're an expert developer with a strong track record.")) {
				return true;}
		}
		else if(perScore>40) {
			if(sugg.equalsIgnoreCase("Great profile! You're a professional developer with solid experience.")) {
				return true;}
		}
		else {
			if(sugg.equalsIgnoreCase("Good start! Keep building your skills and projects to level up.")) {
				return true;
			}
		}
		return false;
	}
	
	public void clickGoBack() {
		driver.findElement(goBackBtn).click();
	}
	
	public boolean checkGoBackNav() {
		if(driver.findElement(measureProButton).isDisplayed()) {
			return true;
		}
		return false;
	}
}
