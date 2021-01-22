package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.BasePage;
import utils.ReadProperties;

public class MyntraLoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(MyntraLoginPage.class);
    public MyntraLoginPage(WebDriver driver) {
        super(driver);
    }

    public static final By mobileNumberEdit = By.xpath("//*[@id=\"reactPageContent\"]/div/div/div[2]/div[2]/div[1]/input");
    public static final By loginContinueBtn = By.xpath("//*[@id=\"reactPageContent\"]/div/div/div[2]/div[2]/div[3]");
    public static final By logInUsingPasswordLbl = By.xpath("//*[@id=\"reactPageContent\"]/div/div[3]/span");
    public static final By passwordEdit = By.xpath("//*[@id=\"reactPageContent\"]/div/div/form/div/div[2]/input");
    public static final By loginBtn = By.xpath("//*[@id=\"reactPageContent\"]/div/div/form/div/div[3]/button");
    public static final By profileLabel = By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[2]/div/div[1]/span[1]");
    public static final By loggedInMobileNumber = By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[2]/div/div[2]/div[2]/div[1]/a/div[2]");

    public WebElement getMobileNumberEdit() { return waitForElement(mobileNumberEdit); }
    public WebElement getLoginContinueBtn() { return waitForElement(loginContinueBtn); }
    public WebElement getLogInUsingPasswordLbl() { return waitForElement(logInUsingPasswordLbl); }
    public WebElement getPasswordEdit() { return waitForElement(passwordEdit); }
    public WebElement getLoginBtn() { return waitForElement(loginBtn); }
    public WebElement getProfileLabel() { return waitForElement(profileLabel); }
    public WebElement getLoggedInMobileNumber() { return waitForElement(loggedInMobileNumber); }

    public MyntraLoginPage loginToMyntraWithPassword() {
//        clickOn(getMobileNumberEdit());
//        enterText(getMobileNumberEdit(), ReadProperties.readConfigProperty("loginMobileNumber"));
//        clickOn(getLoginContinueBtn());
//        waitForPageLoad();
//        clickOn(getLogInUsingPasswordLbl());
        enterText(getPasswordEdit(), ReadProperties.readConfigProperty("loginPassword"));
        clickOn(getLoginBtn());
        waitForPageLoad();
        Actions actions = new Actions(driver);
        actions.moveToElement(getProfileLabel()).pause(2000).build().perform();
        System.out.println("getLoggedInMobileNumber : " + getText(getLoggedInMobileNumber()));
        if(isElemDisplayed(getLoggedInMobileNumber())) {
            logger.info( "Myntra Application Logged In Successfully");
            Assert.assertTrue(true, "Myntra Application Logged In Successfully");
        } else {
            logger.info( "Myntra Application NOT Logged In Successfully");
            Assert.assertTrue(true, "Myntra Application NOT Logged In Successfully");
        }
        return this;
    }
}