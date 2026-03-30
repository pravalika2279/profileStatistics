package testBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {


	 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    // Getter
	    public static WebDriver getDriver() {
	        return driver.get();
	    }
	
	@BeforeTest
	@Parameters({"browser"})
	public void setup(String br)
	{
		WebDriver Webdriver;
		
		switch(br.toLowerCase()) {
		
			case "chrome": {
				Webdriver=new ChromeDriver(); 
				break;
				}
			case "edge" : {
				Webdriver= new EdgeDriver(); 
				break;
				}
			default: {
				System.out.println("Browser is not valid opening chrome");
				Webdriver= new ChromeDriver();
			}
		
		}
		driver.set(Webdriver);
		getDriver().get("https://candid-narwhal-8815b1.netlify.app");
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		getDriver().manage().window().maximize();
	
	}
	
	@AfterTest
	public void tearDown()
	{
		getDriver().quit();
		driver.remove();

	}

}
