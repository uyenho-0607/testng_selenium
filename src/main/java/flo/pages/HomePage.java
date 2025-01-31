package flo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="tab-bar")
    WebElement tabBar;

    public void verifyLoginSuccessed() {
        this.actions.verifyElementDisplayed(tabBar, 10);
    }
}
