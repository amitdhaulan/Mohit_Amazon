package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

	public WebDriver driver;

	By Accountlink = By.xpath("//a[@id='nav-link-accountList']");
	By email = By.xpath("//input[@id='ap_email']");
	By continuebutton = By.xpath("//input[@id='continue']");
	By submit = By.xpath("//input[@id='signInSubmit']");
	By password = By.xpath("//input[@id='ap_password']");
	By validation = By
			.xpath("//div[@class='a-alert-content'][normalize-space()='Enter your email or mobile phone number']");
	By loginassert = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");

	public Login(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;

	}

	public WebElement submit() {

		return driver.findElement(submit);
	}

	public WebElement continuebutton() {

		return driver.findElement(continuebutton);
	}

	public WebElement email() {

		return driver.findElement(email);
	}

	public WebElement password() {

		return driver.findElement(password);
	}

	public WebElement Accountlink() {

		return driver.findElement(Accountlink);
	}

	public WebElement validation() {

		return driver.findElement(validation);
	}

	public WebElement loginassert() {

		return driver.findElement(loginassert);
	}

}
