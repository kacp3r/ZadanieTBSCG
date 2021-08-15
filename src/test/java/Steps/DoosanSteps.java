package Steps;

import POM.Doosan.DoosanHomepagePO;
import POM.Doosan.RegionSelectionPO;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class DoosanSteps {
    Logger logger = Logger.getLogger(DoosanSteps.class);
    static WebDriver driver;
    DoosanHomepagePO doosanHomepagePO;
    RegionSelectionPO regionSelectionPO;

    @Before
    public static void setup() {
        DOMConfigurator.configure("log4j.xml");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public static void close() {
        driver.quit();
    }

    @Given("I open Doosan homepage")
    public void homepage_is_open() {
        doosanHomepagePO = new DoosanHomepagePO(driver);
        doosanHomepagePO.openMe();
    }

    @When("I click the region selection link")
    public void click_region_selection_link() {
        doosanHomepagePO.clickRegionsLink();
        regionSelectionPO = new RegionSelectionPO(driver);
    }

    @When("I select the {word} region in {word} language")
    // TODO {word} will have to be refactored into {string} for region names with two words
    public void click_region_language_button(String region, String language) {
        if (region.equals("India")) {
            if (language.equals("English")) {
                regionSelectionPO.clickIndiaEnglishRegion();
            }
        }

        if (region.equals("Europe")) {
            if (language.equals("German")) {
                regionSelectionPO.clickEuropeGermanRegion();
            }
            // TODO add other languages for Europe
        }
        // TODO add the other regions
    }

    @Then("Homepage is displayed with {word} region and {word} language")
    public void doosan_homepage_is_displayed_with_given_region_and_language(String region, String language) {
        Assert.assertTrue(doosanHomepagePO.isDisplayed());
        Assert.assertTrue(doosanHomepagePO.regionIsSelected(region));
        Assert.assertTrue(doosanHomepagePO.languageIsSelected(language));
    }
}
