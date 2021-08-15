package POM.Bobcat;

import POM.BasePO;
import POM.Doosan.DoosanHomepagePO;
import POM.Doosan.RegionSelectionPO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Do;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class T4BaseModelPO extends BasePO {
    // I considered dividing this into Base Model / Options / Attachments / Summary pages,
    // but I think it's not necessary for this test.

    Logger logger = Logger.getLogger(DoosanHomepagePO.class);

    public T4BaseModelPO(WebDriver driver) {
        super(driver);
    }

    private final String myUrl = "https://buildandquote.bobcat.com/base-model/105";

    private int price;

    // Elements
    final String acceptCookiesSelector = "button[class*=cookie]";
    @FindBy(css = acceptCookiesSelector)
    WebElement acceptCookiesButton;

    @FindBy(xpath = "//span[contains(., 'Continue')]")
    WebElement continueButton;
        // assume cookies are always accepted, so this is the only element with "Continue" text

    @FindBy(css = "[name=first_name]")
    WebElement firstNameInput;

    @FindBy(css = "[name=last_name]")
    WebElement lastNameInput;

    @FindBy(css = "[name=country]")
    WebElement countryDropdown;

    @FindBy(css = "[name=zip]")
    WebElement zipInput;

    @FindBy(css = "[name=phone]")
    WebElement phoneInput;

    @FindBy(css = "[name=email]")
    WebElement emailInput;

    @FindBy(xpath = "//span[contains(., 'Submit')]")
    WebElement submitButton;

    @FindBy(xpath = "//span[text()=\"Finance Amount\"]/following-sibling::span")
    WebElement totalPriceOnSummary;

    @FindBy(css = ".content-submit-form-success")
    WebElement formSubmitSuccessMessage;

    // Actions
    public int getPrice() {
        return this.price;
    }

    public void openMe(){
        logger.trace("Will attempt to open page " + myUrl);
        driver.get(myUrl);
        logger.trace("Finished trying to open page " + myUrl);
        driver.manage().window().maximize();
        this.acceptCookiesIfNecessary();
    }

    public void acceptCookiesIfNecessary() {
        logger.trace("Looking for presence of Accept Cookies button");
        if(driver.findElements(By.cssSelector(acceptCookiesSelector)).size() > 0) {
            logger.trace("We found the accept cookies button, will attempt to click it");
            acceptCookiesButton.click();
        }
    }

    public void clickContinue() {
        logger.trace("Attempting to click Continue button");
        continueButton.click();
        logger.trace("Clicked Continue button");
    }

    public void selectModel(String modelName) {
        logger.trace("Attempting to select model: " + modelName);
        String xpath_locator = "//label[contains(., '" + modelName + "')]";
        WebElement modelButton = driver.findElement(By.xpath(xpath_locator));
        modelButton.click();

        if (modelName.equals("T450 T4 V2 Bobcat Compact Track Loader")) {
            this.price = 50478;
        } else if (modelName.equals("T595 T4 V2 Bobcat Compact Track Loader")) {
            this.price = 59257;
        }

        logger.trace("Selected model: " + modelName);
        logger.trace("Price is now: " + this.price);
    }

    public void selectOption(String group, String option) {
        String group_xpath_selector = "//h3[contains(., '" + group + "')]";
        WebElement group_header = driver.findElement(By.xpath(group_xpath_selector));

        logger.trace("Attempting to click group header: " + group);
        wait.until(ExpectedConditions.elementToBeClickable(group_header));
        group_header.click();
        logger.trace("Clicked group header: " + group);

        String option_xpath_selector = "//label[contains(., '" + option + "')]";
        WebElement option_button = driver.findElement(By.xpath(option_xpath_selector));

        logger.trace("Attempting to click option: " + option);
        option_button.click();
        logger.trace("Clicked option: " + option);

        if (group.equals("Performance Packages")) {
            if (option.equals("P10 Performance Package")) {
                this.price += 0;
            }
            // TODO add other options
        } else if (group.equals("Comfort Packages")) {
            if (option.equals("C10 Comfort Package")) {
                this.price += 495;
            }
        }
        // TODO add other groups
        logger.trace("Current price equals: " + this.price);
    }

    public void selectAttachment(String group, String attachment)
    // TODO this can be optimized, right now it's always stopped by implicit wait
    {
        String group_xpath_selector = "//h3[contains(., '" + group + "')]";
        String attachment_xpath_selector = "//label[contains(., '" + attachment + "')]";

        // if attachment is on screen, then select it
        if (driver.findElements(By.xpath(attachment_xpath_selector)).size() > 0) {
            logger.trace("Looks like attachment is on screen, attempting to click attachment: " + attachment);
            driver.findElement(By.xpath(attachment_xpath_selector)).click();
        } else {
            WebElement group_header = driver.findElement(By.xpath(group_xpath_selector));

            logger.trace("Attempting to click group header: " + group);
            wait.until(ExpectedConditions.elementToBeClickable(group_header));
            group_header.click();
            logger.trace("Clicked group header: " + group);

            WebElement attachment_button = driver.findElement(By.xpath(attachment_xpath_selector));

            logger.trace("Attempting to click option: " + attachment);
            attachment_button.click();
            logger.trace("Clicked option: " + attachment);
        }

        if (group.equals("Water Kit")) {
            if (attachment.equals("Quick-Tach Water Kit")) {
                this.price += 995;
            }
        } else if (group.equals("Vibratory Roller")) {
            if (attachment.equals("48\" Vibratory Roller W/Padded Drum")) {
                this.price += 9463;
            } // TODO add other attachment
        } // TODO add other groups
        logger.trace("Current price is: " + this.price);
    }

    public void fillOutForm(DataTable table) {
        Map<String, String> data = table.asMap(String.class, String.class);

        logger.trace("Attempting to fill out for with the following data:"
                + " " + data.get("First name")
                + " " + data.get("Last name")
                + " " + data.get("Country")
                + " " + data.get("Zip")
                + " " + data.get("Phone")
                + " " + data.get("Email"));

        firstNameInput.sendKeys(data.get("First name"));
        lastNameInput.sendKeys(data.get("Last name"));

        new Select(countryDropdown).selectByVisibleText(data.get("Country"));

        zipInput.sendKeys(data.get("Zip"));
        phoneInput.sendKeys(data.get("Phone"));
        emailInput.sendKeys(data.get("Email"));

        logger.trace("Filled out form");
    }

    public void clickSubmit() {
        logger.trace("Attempting to click Submit button");
        submitButton.click();
        logger.trace("Clicked submit button");
    }

    public boolean verifyPrice() {
        logger.trace("Attempting to check price");
        String priceOnPage = totalPriceOnSummary.getText();
        priceOnPage = priceOnPage.replace("$", "")
                                     .replace(",", "")
                                     .replace(" USD", "");
        float priceOnPageNumeric = Float.parseFloat(priceOnPage);

        logger.trace("Calculated price for the machine is: " + this.price);
        logger.trace("Total price displayed on page is: " + priceOnPageNumeric);
        return this.price==priceOnPageNumeric;
    }

    public boolean formSubmitMessageIsDisplayed() {
        return formSubmitSuccessMessage.isDisplayed();
    }
}
