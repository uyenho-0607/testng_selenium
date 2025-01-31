package flo.base;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {
    private static WebDriver driver;
    private static Map<String, WebDriver> driverMap = new ConcurrentHashMap<>();

    private static void initDriver(String username) {

        Boolean is_headless = ConfigManager.getProperty("headless").equals("true");
        String browser = ConfigManager.getProperty("browser");

        if (driverMap.containsKey(username)) {
            System.out.println("Driver already initiated !");
            return;
        }

        switch (browser) {
            case "chrome":
                ChromeOptions co = new ChromeOptions();
                if (is_headless) {
                    co.addArguments("--headless");
                }

                System.out.println("Init chrome dirver for user: " + username);
                driver = new ChromeDriver(co);
                break;

            case "firefox":
                FirefoxOptions fo = new FirefoxOptions();
                if (is_headless) {
                    fo.addArguments("--headless");
                }

                System.out.println("Init firefox dirver for user: " + username);
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type !!!");
        }
        driverMap.put(username, driver);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver(String username) {
        if (!driverMap.containsKey(username)) {
            initDriver(username);
        }
        return driverMap.get(username);
    }

    public static WebDriver getDriver() {
        String default_user = ConfigManager.getProperty("owner");
        return getDriver(default_user);
    }

    public static Collection<WebDriver> getAllDrivers() {
        return driverMap.values();
    }

    public static void quitDriver() {
        String default_user = ConfigManager.getProperty("owner");
        quitDriver(default_user);
    }

    public static void quitDriver(String username) {
        if (!driverMap.containsKey(username)) {
            return;
        }
        driverMap.get(username).quit();
        driverMap.remove(username);
    }

    public static void quitAllDrivers() {
        for (WebDriver webDriver : getAllDrivers()) {
            webDriver.quit();
        }
        driverMap.clear();
    }
}
