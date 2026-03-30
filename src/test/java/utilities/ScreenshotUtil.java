package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath =
                System.getProperty("user.dir")
                + "/screenshots/"
                + testName + "_" + timeStamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            Files.copy(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}
