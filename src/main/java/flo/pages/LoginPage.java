package flo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
// import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login-form-username")
    private WebElement username;

    @FindBy(id = "txtPassword")
    private WebElement password;

    @FindBy(css = ".btn.btn-default")
    private WebElement signinBtn;

    public void login(String username, String password) {
        this.actions.sendKeys(this.username, username, 10);
        this.actions.sendKeys(this.password, password, 10);
        this.actions.click(this.signinBtn, 10);
    }
    
}
