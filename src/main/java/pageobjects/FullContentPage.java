package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.Base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class FullContentPage extends Base {

    private WebDriver driver;
    private By newsTitle = By.cssSelector("[class='article__title']");
    private By linksInTheNews = By.cssSelector("[class='article-content'] a");
    private ExecutorService executorService;

    public FullContentPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getNewsTitle() {
        return driver.findElement(newsTitle);
    }

    public boolean verifyTitle() {
        String newsTitle = getNewsTitle().getText();
        String pageTitle = driver.getTitle();
        return pageTitle.contains(newsTitle);
    }

    public boolean checkLinksInContent(int numberOfThreads) {
        List<WebElement> links = driver.findElements(linksInTheNews);
        AtomicBoolean allLinksValid = new AtomicBoolean(true);
        executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (WebElement link : links) {
            String linkUrl = link.getAttribute("href");
            if (linkUrl != null) {
                executorService.execute(() -> {
                    boolean isValid = verifyLink(linkUrl);
                    if (!isValid) {
                        allLinksValid.set(false);
                    }
                });
            }
        }

        shutdownExecutorService();

        return allLinksValid.get();
    }

    private boolean verifyLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            accessLog().info("Success Url: " + url + " Response Code: " + responseCode);
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            accessLog().error("Failed Url: " + url + " - Reason: " + e.getMessage());
            return false;
        }
    }

    private void shutdownExecutorService() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
