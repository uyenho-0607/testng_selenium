package flo;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import flo.base.BaseTest;
import flo.base.ConfigManager;
import flo.listeners.ExtentReportListeners;


@Listeners(ExtentReportListeners.class)
public class LoginTest extends BaseTest {

	@Test
	public void testLoginSuccess() {
		ExtentTest test = ExtentReportListeners.getTest();

        test.log(Status.INFO, "Step 1: Login with valid username and password");
		loginPage.login(ConfigManager.getProperty("owner"), ConfigManager.getProperty("password"));
		
		test.log(Status.INFO, "Step 2: Verify login success");
		homePage.verifyLoginSuccessed();
	}

}
