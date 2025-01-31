package flo.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import flo.pages.HomePage;
import flo.pages.LoginPage;

public class BaseTest {
    private static WebDriver driver;
    protected static LoginPage loginPage;
    protected static HomePage homePage;

    @BeforeClass
    public void setUp(){
        driver = WebDriverManager.getDriver();
        driver.get(ConfigManager.getProperty("baseUrl"));


        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.quitAllDrivers();
    }



}
