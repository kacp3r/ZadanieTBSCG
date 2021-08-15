package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePO {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    public BasePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // page factory daje to, że elementy są inicjalizowane, ale przeszukiwanie dopiero przy użyciu
        // chodzi o elementy zainicjalizowane za pomocą @FindBy
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);
    }

    protected void waitAndClick(WebElement e){
        wait.until(ExpectedConditions.elementToBeClickable(e));
        e.click();
    }
}