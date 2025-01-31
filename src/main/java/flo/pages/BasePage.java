package flo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import flo.utils.Elements;

public class BasePage {
    protected WebDriver dirver;
    protected Elements actions;
    
    public BasePage (WebDriver driver) {
        this.dirver = driver;
        this.actions = new Elements(driver);
        PageFactory.initElements(driver, this);
    }
}
