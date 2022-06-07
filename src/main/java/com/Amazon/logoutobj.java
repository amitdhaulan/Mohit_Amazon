package com.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class logoutobj {

	public WebDriver driver;

	public logoutobj(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;

	}

	By logout = By.xpath("//span[contains(text(),'Sign Out')]");
	By Accountlink = By.xpath("//a[@id='nav-link-accountList']");
	By logoutassert = By.xpath("//h1[@class='a-spacing-small']");

	// methods for calling webElements
	public WebElement logout() {

		return driver.findElement(logout);

	}

	public WebElement account() {

		return driver.findElement(Accountlink);
	}

	public WebElement logoutassert() {

		return driver.findElement(logoutassert);
	}

}
