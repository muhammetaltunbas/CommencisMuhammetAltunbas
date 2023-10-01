package stepDefinitions;

import org.junit.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import pageobjects.FullContentPage;
import pageobjects.HomePage;
import resources.Base;

public class TestNews extends Base {
    public HomePage hp;
    public FullContentPage fp;
    private int numberOfThreads=10;// For multi-threading

    @Given("Go Homepage")
    public void go_homepage() throws IOException {
        driver = initializeDriver();
        driver.get("https://techcrunch.com/");
    }

    @Then("Homepage is opened")
    public void homepage_is_opened() {
        hp = new HomePage(driver);
        Assert.assertTrue(hp.isHomePageOpen());
    }

    @And("Check if each latest news has an author and an image")
    public void check_if_each_latest_news_has_an_author_and_an_image() {
        Assert.assertTrue(hp.areAllNewsValid());
    }

    @And("Close browser")
    public void close_browser() {
        driver.quit();
    }
    @When("Click for {int} news")
    public void click_for_news(int index) {
        hp.clickAnyNews(index);
    }
    @Then("Check if the browser title is the same with the news title")
    public void check_if_the_browser_title_is_the_same_with_the_news_title() {
        fp= new FullContentPage(driver);
        Assert.assertTrue(fp.verifyTitle());
    }
    @And("Check the links within the news content")
    public void check_the_links_within_the_news_content(){
        Assert.assertTrue(fp.checkLinksInContent(numberOfThreads));
    }


}
