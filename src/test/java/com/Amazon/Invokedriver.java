package com.Amazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.Assertion;

public class Invokedriver {

    public WebDriver driver;
    public static Properties prop;

    //Invoke driver generic method
    public WebDriver indriver() throws IOException {
        loadProperty();
        String url = prop.getProperty("url");
        String browsername = prop.getProperty("browser");

        if (browsername.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", ".\\exefiles/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get(url);
            Reporter.log("firefox launched", true);

        } else if (browsername.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ".\\exefiles/chromedriver.exe");
            driver = new ChromeDriver();
            // driver= new RemoteWebDriver(DesiredCapabilities.chrome());
            driver.get(url);
            implicitWaitForSeconds(driver, 15);
            driver.manage().window().maximize();
            Reporter.log("Chrome launched", true);
        } else if (browsername.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", ".\\exeFiles/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            // driver= new RemoteWebDriver(DesiredCapabilities.internetExplorer());
            driver.get(url);
            Reporter.log("IE launched", true);
        }
        return driver;
    }

    //Generic method for loading property file.
    public static void loadProperty() throws IOException {
        try (InputStream input = Invokedriver.class.getClassLoader().getResourceAsStream(".\\resource/data.properties")) {
            prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            FileInputStream fil1 = new FileInputStream(
                    ".\\resource/data.properties");
            prop.load(fil1);
        } catch (IOException exception) {
            System.out.println(exception.getLocalizedMessage());
        }

    }

    //Screenshot method for fail test cases generic method
    public static void TakeScreenshot(String testcaseName, WebDriver driver) throws IOException {
        // Creating instance of File
        File File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationfile = System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png";
        FileUtils.copyFile(File, new File(destinationfile));
        Reporter.log("Loading property file", true);
    }

    //Explicit wait generic method
    public void explicitWait(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Reporter.log("Explicitwait complete", true);
    }

    //implicit wait generic method
    public static void implicitWaitForSeconds(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    //generic method for Assertion
    public static Assertion asser() {
        Assertion assertion1 = new Assertion();
        return assertion1;

    }


    public static void iselement() {

    }

}
