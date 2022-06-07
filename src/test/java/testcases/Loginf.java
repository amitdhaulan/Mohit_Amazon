/*package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.Amazon.Invokedriver;
import com.Amazon.Login;

@Test
public class Loginf extends Invokedriver {

	public WebDriver driver;
	
	public void validatelogin() throws IOException {
		
		
		driver = indriver();
		driver.manage().window().maximize();  
		driver.get("https://www.amazon.in/");
		Login lg= new Login(driver);
		lg.Accountlink().click();
		lg.continuebutton().click();
		lg.email().sendKeys("mohitsisodia2548");
		lg.continuebutton().click();
		lg.password().sendKeys("");
		Reporter.log("user password not present", true);
		lg.submit().click();
		
		
	}
	
}*/