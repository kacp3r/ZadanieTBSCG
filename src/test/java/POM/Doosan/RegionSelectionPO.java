package POM.Doosan;

import POM.BasePO;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegionSelectionPO extends BasePO {
    Logger logger = Logger.getLogger(DoosanHomepagePO.class);

    public RegionSelectionPO(WebDriver driver) {
        super(driver);
    }

    private final String myUrl = "https://doosanportablepower.com/dpp-na/na/en/regions";

    // Elements
    @FindBy(css = "a[href*=\"in/en\"]")
    private WebElement selectIndiaEnglishButton;

    @FindBy(css = "a[href*=\"eu/de\"]")
    private WebElement selectEuropeGermanButton;

    //Actions
    public void clickIndiaEnglishRegion() {
        logger.trace("Will attempt to click on India English region button");
        Actions action = new Actions(driver);
        action.moveToElement(selectIndiaEnglishButton).perform();
        selectIndiaEnglishButton.click();
        logger.trace("Clicked on India English region button");
    }

    public void clickEuropeGermanRegion() {
        logger.trace("Will attempt to click on Europe German region button");
        Actions action = new Actions(driver);
        action.moveToElement(selectEuropeGermanButton).perform();
        selectEuropeGermanButton.click();
        logger.trace("Clicked on Europe German region button");
    }
}
