package flo.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import flo.base.WebDriverManager;

public class ExtentReportListeners implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Uyn");
        extent.setSystemInfo("Environment", "Stage");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test case and store it in ThreadLocal
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = getTest();
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.fail(result.getThrowable());

        // Take and attach a screenshot
        for (WebDriver driver : WebDriverManager.getAllDrivers()) {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.addScreenCaptureFromBase64String(base64Screenshot, result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Getter for ExtentTest (prevents null)
    public static ExtentTest getTest() {
        return testThread.get();
    }
}
