package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AnalyticsPage;
import pageObjects.HeaderSection;
import pageObjects.HomePage;

import testBase.BaseClass;

import utilities.ExtentListener;
import com.aventstack.extentreports.Status;

public class ProfileStrengthCheck extends BaseClass{
	
	public static int score;
	
	private HeaderSection hs;
	private HomePage hp;
	private AnalyticsPage ap;
	
	@BeforeClass
	public void inits() {
		hs= new HeaderSection(getDriver());
		ap= new AnalyticsPage(getDriver());
		hp= new HomePage(getDriver());
	}
	
	@Test(priority=5)
	public void analyticsNavCheckInHome1() {
		ExtentListener.test.get().log(Status.INFO, "Navigating to Analytics via link provided in Home before any reports");
		hs.ThemeChange();
		
		hp.clickAnalyticPreviewLink();
		Assert.assertEquals(ap.checkAnalyticsNavigationForFirstTime(),true);
		ExtentListener.test.get().log(Status.PASS, "Analytics page opened successfully");
	}
	
	
	@Test(priority=6, dataProvider="ValidProfileData", groups={"Sanity"})
	public void validCheckProfileform(String yoe, String wd, String am) {

		ExtentListener.test.get().log(Status.INFO,"Entering profile data - Experience: " + yoe +", Websites: " + wd +", Apps: " + am);

		hs.clickHome();
		hp.enterDetails(yoe, wd, am);
		hp.clickMesaureProfile();
		
		score=ap.calculateScore(yoe,Integer.parseInt(wd),Integer.parseInt(am));
		ExtentListener.test.get().log(Status.INFO, "Expected Calculated profile score: " + score);
		
		SoftAssert soft= new SoftAssert();
		soft.assertEquals(ap.checkPercentScore(score),true);
		soft.assertEquals(ap.checkBadge(score),true);
		soft.assertEquals(ap.checkresultInfo(yoe, wd, am), true);
		soft.assertEquals(ap.checkSuggestion(score), true);
		soft.assertAll();
		ExtentListener.test.get().log(Status.PASS, "Profile strength validated successfully");
	}
	
	@Test(priority=7, dataProvider="InvalidProfileData")
	public void invalidCheckProfileForm(String yoe, String wd, String am) {

		ExtentListener.test.get().log(Status.INFO,"Submitting invalid profile data - " + yoe + ", " + wd + ", " + am);

		hs.clickHome();
		hp.enterDetails(yoe, wd, am);
		hp.clickMesaureProfile();
		try {
			Alert alert= getDriver().switchTo().alert();
			alert.accept();
			Assert.assertTrue(true);
			ExtentListener.test.get().log(Status.PASS, "Validation alert displayed and accepted");
		}
		catch(NoAlertPresentException e) {
			ExtentListener.test.get().log(Status.FAIL, "Expected alert was not shown");
			Assert.assertTrue(false);
		}
		
		
	}
	
	@Test(priority=8)
	public void analyticsNavCheckInHome2() {
		hs.clickHome();
		
		ExtentListener.test.get().log(Status.INFO, "Navigating to Analytics via link provided in Home after getting some previous reports");
		hp.clickAnalyticPreviewLink();
		
		Assert.assertEquals(ap.checkAnalyticsNavigationWithSavedData(),true);
		ExtentListener.test.get().log(Status.PASS, "Analytics page opened successfully");
	}
	
	
	@Test(priority=9)
	public void checkAnalyzeAnotherProfileLink() {
		ExtentListener.test.get().log(Status.INFO, "Navigating to Home Page by link provided in Analytics Page");
		ap.clickGoBack();
		
		ap.checkGoBackNav();
		Assert.assertEquals(hp.checkHomeNavigation(), true);
		ExtentListener.test.get().log(Status.PASS, "Home page opened successfully");
	}
	
	@DataProvider
	public Object[][] ValidProfileData(){
		return new Object[][] {
			{"0-1 years","0","0"},
			{"0-1 years","1","1"},
			{"0-1 years", "5", "5"},
			{"2-5 years", "0","0"},
			{"2-5 years", "1","1"},
			{"2-5 years","25","25"},
			{"6-10 years","0","0"},
			{"6-10 years","1","1"},
			{"6-10 years", "75","75"},
			{"10+ years","0","0"},
			{"10+ years","250","250"},
			{"10+ years","1","1"}
		};
	}
	
	@DataProvider
	public Object[][] InvalidProfileData(){
		return new Object[][] {
			{"0-1 years","6","6"},
			{"2-5 years","26","26"},
			{"6-10 years","76","76"},
			{"10+ years","260","260"}
		};
	}
	
}

