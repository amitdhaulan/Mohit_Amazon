package com.Amazon;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Loginmethod extends Invokedriver {

    public WebDriver driver;

    // Login method for login user
    public void login(WebDriver driver) throws IOException {
        Login lg = new Login(driver);

        lg.Accountlink().click();
        lg.continuebutton().click();
        lg.validation().getText().equalsIgnoreCase("");

        asser().assertEquals("Enter your email or mobile phone number", lg.validation().getText());
        Reporter.log("Validation message displayed", true);
        Invokedriver.loadProperty();


        lg.email().sendKeys(prop.getProperty("namem"));


        lg.continuebutton().click();
        lg.password().sendKeys(prop.getProperty("password"));
        lg.submit().click();
        Reporter.log("logged in successfully", true);
        asser().assertEquals("Hello, mohit", lg.loginassert().getText());
    }
}
