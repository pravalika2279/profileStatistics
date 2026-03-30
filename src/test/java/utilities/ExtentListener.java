package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;

import testBase.BaseClass;

public class ExtentListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtentReport();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass(" Test Passed");
        logNamedParameters(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(" Test Failed");
        test.get().fail(result.getThrowable());

        logNamedParameters(result);

        //  Capture Screenshot
        String screenshotPath = ScreenshotUtil.captureScreenshot(
                BaseClass.getDriver(),
                result.getMethod().getMethodName()
        );

        try {
            test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(" Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // DataProvider logger
    private void logNamedParameters(ITestResult result) {
        Object[] params = result.getParameters();

        if (params != null && params.length == 3) {
            test.get().info(" Test Data Used:");
            test.get().info("Experience : " + params[0]);
            test.get().info("Websites   : " + params[1]);
            test.get().info("Apps       : " + params[2]);
        }
    }
}
