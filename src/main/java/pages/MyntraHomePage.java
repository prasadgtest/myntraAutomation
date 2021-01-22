package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import utils.BasePage;
import org.testng.Assert;
import utils.ReadProperties;


public class MyntraHomePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(MyntraHomePage.class);
    public MyntraHomePage(WebDriver driver) {
        super(driver);
    }

    public static final By profileLabel = By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[2]/div/div[1]/span[1]");
    public static final By loginORSignUp = By.xpath("//*[@href='/login?referer=https://www.myntra.com/']");
    public static final By mobileNumber = By.xpath("//*[@id=\"reactPageContent\"]/div/div/div[2]/div[2]/div[1]/input");
    public static final By wishListLabel = By.xpath("//*[@id=\"mountPoint\"]/div/div[2]/div[1]/div[3]/a[1]/span[2]");

    public WebElement getProfileLabel() { return waitForElement(profileLabel); }
    public WebElement getLoginORSignUp() { return waitForElement(loginORSignUp); }
    public WebElement getMobileNumber() { return waitForElement(mobileNumber); }
    public WebElement getWishListLabel() { return waitForElement(wishListLabel); }



    public MyntraHomePage navigateToMyntraWebAppln() {
        driver.get(ReadProperties.readConfigProperty("appURL"));
        driver.manage().window().maximize();
        waitForPageLoad();
        if(getWishListLabel().isDisplayed()) {
            logger.info("Myntra Application Launched Successfully");
            Reporter.log("fgsdfgsdfgdsMyntra Application Launched Successfully");
            Assert.assertTrue(true, "Myntra Application Launched Successfully");
        } else {
            logger.info("Myntra Application NOT Launched Successfully");
            Assert.assertTrue(false, "Myntra Application NOT Launched Successfully");
        }
        return this;
    }

    public MyntraLoginPage navigateToLoginPage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(getProfileLabel())
                .pause(1000).build().perform();
        clickOn(getLoginORSignUp());
        waitForPageLoad();
        if(isElemDisplayed(getMobileNumber())) {
            logger.info("Myntra Application Login Page Displayed Successfully");
            Assert.assertTrue(true, "Myntra Application Login Page Displayed Successfully");
        } else {
            logger.info("Myntra Application Login Page NOT Displayed Successfully");
            Assert.assertTrue(false, "Myntra Application Login Page NOT Displayed Successfully");
        }
        return new MyntraLoginPage(driver);
    }
}
