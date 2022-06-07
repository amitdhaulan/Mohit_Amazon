package testcases;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import java.util.Set;
import org.apache.logging.log4j.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Amazon.Homeobj;
import com.Amazon.Invokedriver;
import com.Amazon.Loginmethod;
import com.Amazon.logoutmethod;

/*
 * •	Framework of your choice  -- Not properly used -- (06/02/2022: Done)
   •	POM/Page factory -- Not properly used -- (06/02/2022: Still some class names are not as per POM)
   •	Code re-usability -- Not used -- (06/02/2022: Done)
   •	OOPS concepts -- Not used -- (06/02/2022: Done)
   •	Reports (Pass/Fails TCs) -- Used -- (06/02/2022: Done)
   •	Screenshots for the failed steps -- Used -- (06/02/2022: Done)



 * 
 * Remove unused code
 * Use comments for used functions
 * Use code optimization and code re-usability
 * Assert for all the test cases
 * Use wait (implicit/explicit/fluent) instead of sleep
 * Use property file instead of class files to store any static data
 * Use getters to get the DOM elements
 * Properly use OOPS concepts
 * Use proper names for the functions
 *  
 */

public class HomeTest extends Invokedriver {

	public WebDriver driver;

	@Test
	public void Home() throws IOException {

		// Invoking driver
		driver = indriver();
		Loginmethod lm = new Loginmethod();
		lm.login(driver);

		// creating object of Homeobject class
		Homeobj lg = new Homeobj(driver);

		loadProperty();
		String productcategory = prop.getProperty("productcategory");
		String selectitem = prop.getProperty("selectproduct");

		// Searching the product
		lg.searchbar().sendKeys(productcategory);
		lg.Searchbutton().click();
		Reporter.log("searching product", true);
		// using explicitwait for product loading
		explicitWait(lg.productlist());

		// selecting the product
		productsearch(selectitem);
		Reporter.log("product searched", true);

		// moving driver from parent to child window
		Windowhandle();

		lg.addtocart().click();
		asser().assertEquals("Added to Cart", lg.addcartassert().getText());
		Reporter.log("Item added to cart", true);
		lg.itemdbuy().click();

		explicitWait(lg.usethisaddress());
		lg.usethisaddress().click();

		// getting total item price
		String itemprice = lg.price().getText();
		System.out.println("Order total =" + itemprice);
		Reporter.log("Checkout page", true);

		// user logout
		logoutmethod logoutobj = new logoutmethod();
		lg.Amazonicon().click();
		lg.popbutton().click();
		logoutobj.logout(driver);
		driver.quit();

	}

	// method for searching the product
	public void productsearch(String prodname) {

		Homeobj lst = new Homeobj(driver);
		List<WebElement> produccts = lst.productsearchlist();

		System.out.println("Total number of products on page:" + produccts.size());

		for (int i = 0; i < produccts.size(); i++) {
			String name = produccts.get(i).getText();
			if (name.equalsIgnoreCase(prodname)) {
				System.out.println(produccts.get(i).getText());
				driver.findElements(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']")).get(i)
						.click();
				Reporter.log("selected the product", true);
			}

		}

	}

// method for handling the window 
	public void Windowhandle() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentwindow = it.next();
		String childwindow = it.next();
		driver.switchTo().window(childwindow);
	}

}
