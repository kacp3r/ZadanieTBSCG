package Steps;

import POM.Bobcat.T4BaseModelPO;
import io.cucumber.datatable.DataTable;
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

public class BobcatSteps {
    Logger logger = Logger.getLogger(DoosanSteps.class);
    static WebDriver driver;
    T4BaseModelPO t4BaseModelPO;

    @Before
    public static void setup() {
        DOMConfigurator.configure("log4j.xml");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public static void close() {
        driver.quit();
    }

    @Given("I open model T4 page")
    public void model_page_is_open() {
        t4BaseModelPO = new T4BaseModelPO(driver);
        t4BaseModelPO.openMe();
    }

    @When("I select model {string}")
    public void select_model(String model) {
        t4BaseModelPO.selectModel(model);
    }

    @When("I click continue")
    public void click_continue() {
        t4BaseModelPO.clickContinue();
    }

    @When("I select option {string} from group {string}")
    public void select_option(String option, String group) {
        t4BaseModelPO.selectOption(group, option);
    }

    @When("I select attachment {string} from group {string}")
    public void select_attachment(String option, String group) {
        t4BaseModelPO.selectAttachment(group, option);
    }

    @When("I fill out the form with valid data")
    public void fill_out_form(DataTable table) {
        t4BaseModelPO.fillOutForm(table);
    }

    @When("I click Submit")
    public void click_submit() {
        t4BaseModelPO.clickSubmit();
    }

    @Then("Something happens")
    public void something_happens() {
        Assert.assertTrue(true);
    }

    @Then("The price is correct according to calculation")
    public void price_is_correct() {
        Assert.assertTrue(t4BaseModelPO.verifyPrice());
    }

    @Then("I can see message saying that configuration has been sent to local dealer")
    public void configuration_sent_message_is_displayed() {
        Assert.assertTrue(t4BaseModelPO.formSubmitMessageIsDisplayed());
    }
}
