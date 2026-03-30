package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReport() {

        if (extent == null) {
            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Profile Statistics Test Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Tester", "Pravalika");
            extent.setSystemInfo("Browser", "Chrome / Edge");
            extent.setSystemInfo("Framework", "Selenium TestNG");
        }
        return extent;
    }
}
