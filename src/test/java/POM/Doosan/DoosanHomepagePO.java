package POM.Doosan;

import POM.BasePO;
import POM.Doosan.RegionSelectionPO;
import io.cucumber.java.eo.Do;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DoosanHomepagePO extends BasePO {
    Logger logger = Logger.getLogger(DoosanHomepagePO.class);

    public DoosanHomepagePO(WebDriver driver) {
        super(driver);
    }

    private final String myUrl = "https://doosanportablepower.com/dpp-na/na/en";

    // Elements
    @FindBy(css = ".navbar>div>[href*=regions]")
    private WebElement regionChoiceButton;

    @FindBy(css = "#hero")
    private WebElement featureCarousel;

    final String acceptCookiesSelector = "[id=\"onetrust-accept-btn-handler\"]";
    @FindBy(css = acceptCookiesSelector)
    private WebElement acceptCookiesButton;

    // Actions
    public void openMe(){
        logger.trace("Will attempt to open page " + myUrl);
        driver.get(myUrl);
        logger.trace("Finished trying to open page " + myUrl);
        driver.manage().window().maximize();
        this.acceptCookiesIfNecessary();
    }

    public boolean isDisplayed(){
        logger.trace("Looking for feature carousel object, which would mean that the page is open.");
        wait.until(ExpectedConditions.stalenessOf(featureCarousel));
        if (featureCarousel.isDisplayed()) {
            logger.debug("We found the feature carousel object, which means that the page is displayed");
        }
        return featureCarousel.isDisplayed();
    }

    public void acceptCookiesIfNecessary() {
        logger.trace("Looking for presence of Accept Cookies button");
        if(driver.findElements(By.cssSelector(acceptCookiesSelector)).size() > 0) {
            logger.trace("We found the accept cookies button, will attempt to click it");
            acceptCookiesButton.click();
        }
    }

    public void clickRegionsLink(){
        logger.trace("Will attempt to click on regions choice link");
        regionChoiceButton.click();
        logger.trace("Clicked on regions choice link");
    }

    public boolean regionIsSelected(String region) {
        logger.trace("Looking for " + region + " in page title");
        if (driver.getTitle().contains(region)) {
            logger.debug("We found the word " + region + " in the title, which means that the " + region + " region is displayed");
        }
        return driver.getTitle().contains(region);
    }

    public boolean languageIsSelected(String language) {
        String language_translated = language;
        // if it's English, we do not change assignment
        if (language.equals("German")) {
            language_translated = "Deutsch";
            // TODO add other languages
        }

        logger.trace("Looking for " + language_translated + " in region selection button link text");
        if (regionChoiceButton.getAttribute("innerHTML").contains(language_translated)) {
            logger.trace("We found the word " + language_translated + " in the region selection button link text," +
                    " which means the " + language + " language is selected");
        }
        return regionChoiceButton.getAttribute("innerHTML").contains(language_translated);
    }

}
