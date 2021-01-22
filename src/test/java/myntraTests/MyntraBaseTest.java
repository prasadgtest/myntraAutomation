package myntraTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.MyntraHomePage;
import pages.MyntraLoginPage;
import pages.MyntraSearchPage;
import utils.ReadProperties;

public class MyntraBaseTest {
    public WebDriver driver;
    public MyntraHomePage myntraHomePage;
    public MyntraLoginPage myntraLoginPage;
    public MyntraSearchPage myntraSearchPage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void classLevelSetup() {
        String browserVersion = ReadProperties.readConfigProperty("chrome.version");
        WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
    }

    @BeforeMethod
    public void methodLevelSetup() {
        myntraHomePage = new MyntraHomePage(driver);
        myntraLoginPage = new MyntraLoginPage(driver);
        myntraSearchPage = new MyntraSearchPage(driver);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
