package com.Amazon;

import java.io.*;
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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.Assertion;

import static java.lang.System.exit;

public class Invokedriver {

    public WebDriver driver;
    public static Properties prop;

    //Invoke driver generic method
    public WebDriver indriver() throws IOException {
        loadProperty();

        String url = getCustomProperty("url1");
        String browsername = getCustomProperty("browser");

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
            System.setProperty("webdriver.ie.driver", ".\\exefiles/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            // driver= new RemoteWebDriver(DesiredCapabilities.internetExplorer());
            driver.get(url);
            Reporter.log("IE launched", true);
        }
        return driver;
    }

    //Generic method for loading property file.
    public void loadProperty() throws IOException {
        String fileName = ".\\resource\\data.properties";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        } finally {
            fileInputStream.close();
        }
    }

    public String getCustomProperty(String property) throws IOException {
        loadProperty();
        if (prop.getProperty(property) == null) {
            System.out.println("Error! Key --> "+property+" is not found in property file");
            Assert.assertEquals(true, false);
            exit(0);
        }
        return prop.getProperty(property);
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
