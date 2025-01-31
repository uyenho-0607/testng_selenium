package flo.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Elements {
	protected WebDriver driver;
	private WebDriverWait wait;

	public Elements(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
	}

	private WebElement waitForElementClickable(WebElement element, int timeout) {
		WebDriverWait driverWait = this.wait;

		if (timeout != Constants.EXPLICIT_WAIT) {
			driverWait = new WebDriverWait(this.driver, Duration.ofSeconds(timeout));
		}

		WebElement ele = null;
		Logging.info("Waiting for element: " + Common.getElementLocator(element) + " to be clickable");
		try {
			ele = driverWait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (TimeoutException e) {
			Logging.error("Element " + Common.getElementLocator(element) + " not clickable !");
			throw new NoSuchElementException("Element not clickable: " + Common.getElementLocator(element));
		}
		return ele;
	}

	private WebElement waitForElementVisible(WebElement element, int timeout) {
		WebDriverWait driverWait = this.wait;

		if (timeout != Constants.EXPLICIT_WAIT) {
			driverWait = new WebDriverWait(this.driver, Duration.ofSeconds(timeout));
		}

		WebElement ele = null;
		Logging.info("Waiting for element: " + Common.getElementLocator(element) + " to be visible");

		try {
			ele = driverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			Logging.error("Element " + Common.getElementLocator(element) + " not visible !");
			throw new NoSuchElementException("Element not visible: " + Common.getElementLocator(element));
		}
		return ele;
	}

	private boolean isDisplayed(WebElement element, int timeout) {
		try {
			return waitForElementVisible(element, timeout).isDisplayed();
		} catch (NoSuchElementException e) {
			Logging.warn("[isDisplayed] Element " + Common.getElementLocator(element) + " is not displayed.");
			return false;
		}
	}

	public Elements click(WebElement element, int timeout) {
		WebElement ele = this.waitForElementClickable(element, timeout);
		if (ele != null) {
			Logging.info("Clicking on element: " + Common.getElementLocator(element));
			ele.click();
		}
		return this;
	}

	public Elements sendKeys(WebElement element, String value, int timeout) {
		WebElement ele = this.waitForElementVisible(element, timeout);
		if (ele != null) {
			ele.clear();
			ele.sendKeys(value);
		}
		return this;
	}

	public Elements verifyElementDisplayed(WebElement element, int timeout) {
		Assert.assertTrue(this.isDisplayed(element, timeout),
				"Element: " + Common.getElementLocator(element) + " is not displayed!");
		return this;
	}
}
