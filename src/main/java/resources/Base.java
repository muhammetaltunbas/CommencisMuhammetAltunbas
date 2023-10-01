package resources;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    public static WebDriver driver;
    public static WebDriverWait wait;
    private static Logger log = LogManager.getLogger(Base.class.getName());

    public Logger accessLog() {
        return log;
    }

    public WebDriver initializeDriver() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        return driver;
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;

    }

    public void waitFunctionClickable(By locator) {
        if (wait == null) {
            wait = new WebDriverWait(driver, 5);
        }
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitFunctionVisibility(By locator) {
        if (wait == null) {
            wait = new WebDriverWait(driver, 5);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
