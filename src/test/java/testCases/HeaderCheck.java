package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AnalyticsPage;
import pageObjects.BasePage;
import pageObjects.HeaderSection;
import pageObjects.HomePage;
import testBase.BaseClass;

import utilities.ExtentListener;
import com.aventstack.extentreports.Status;

public class HeaderCheck extends BaseClass {
	
	private HeaderSection hs;
	private BasePage bp;
	private HomePage hp;
	private AnalyticsPage ap;
	
	@BeforeClass
	public void inits() {
		hs= new HeaderSection(getDriver());
		bp= new BasePage(getDriver());
		ap= new AnalyticsPage(getDriver());
		hp= new HomePage(getDriver());
	}
	
	@Test(priority=1)
	public void headerCheck() {
		ExtentListener.test.get().log(Status.INFO, "Verifying header title");
		boolean headingCheck= hs.checkHeading();
		
		Assert.assertTrue(headingCheck);
		ExtentListener.test.get().log(Status.PASS, "Header title verified successfully");
		
	}
	
	@Test(priority=2)
	public void homeNavigationCheck() {
		ExtentListener.test.get().log(Status.INFO, "Clicking Home link");
		hs.clickHome();
		
		boolean homeCheck= hp.checkHomeNavigation();
		Assert.assertTrue(homeCheck);
		ExtentListener.test.get().log(Status.PASS, "Home navigation successful");
	}
	
	@Test(priority=3)
	public void analyticsNavigationCheck() {
		ExtentListener.test.get().log(Status.INFO, "Navigating to Analytics page");
		hs.clickAnalytics();
		
		boolean analyticsCheck= ap.checkAnalyticsNavigationForFirstTime();
		Assert.assertTrue(analyticsCheck);
		ExtentListener.test.get().log(Status.PASS, "Analytics page opened");
		
		hs.clickHome();
	}
	
	@Test(priority=4)
	public void ThemeCheck() {
		ExtentListener.test.get().log(Status.INFO, "Checking Light theme");
		boolean lightCheck= bp.checkLightTheme();
		Assert.assertTrue(lightCheck);
		ExtentListener.test.get().log(Status.PASS, "Light Theme switched successfully");
		
		ExtentListener.test.get().log(Status.INFO, "Switching to Dark theme");
		hs.ThemeChange();
		
		boolean darkCheck= bp.checkDarkTheme();
		Assert.assertTrue(darkCheck);
		ExtentListener.test.get().log(Status.PASS, "Dark Theme switched successfully");
		
		hs.clickHome();
	}
	
}
