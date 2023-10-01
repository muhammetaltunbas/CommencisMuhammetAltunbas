package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.Base;
import java.util.List;

public class HomePage extends Base {
    private WebDriver driver;
    private String pageUrl = "https://techcrunch.com/";
    private By latestNewsList = By.cssSelector("[class*='post-block--unread']");
    private By authorsList = By.cssSelector("[class*='river-byline__authors']");
    private By imagesList = By.cssSelector("[class*='post-block__media'] img");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getLatestNews() {
        return driver.findElements(latestNewsList);
    }

    public boolean isHomePageOpen() {
        return driver.getCurrentUrl().equalsIgnoreCase(pageUrl);
    }

    public boolean areAllNewsValid() {
        for (WebElement newsElement : getLatestNews()) {
            WebElement authorElement = newsElement.findElement(authorsList);
            WebElement imageElement = newsElement.findElement(imagesList);
            String authorText = authorElement.getText();
            if (authorText.isEmpty() || !imageElement.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public void clickAnyNews(int index) {
        List<WebElement> newsList = getLatestNews();
        if (index >= 0 && index < newsList.size()) {
            newsList.get(index).click();
        } else {
            accessLog().error("Trying to click invalid news index.");
        }
    }
}
