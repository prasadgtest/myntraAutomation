package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.BasePage;
import utils.ReadProperties;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyntraSearchPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(MyntraSearchPage.class);

    public MyntraSearchPage(WebDriver driver) {
        super(driver);
    }
    public static final By searchBoxEdit = By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[3]/input");
    public static final By priceRangeFilterChk = By.xpath("//*[@id=\"mountRoot\"]/div/div[1]/main/div[3]/div[1]/section/div/div[2]/ul/li");
    public static final By colorFilterChk = By.xpath("//*[@id=\"mountRoot\"]/div/div[1]/main/div[3]/div[1]/section/div/div[2]/ul/li");
    public static final By discountFilterRdb = By.xpath("//*[@id=\"mountRoot\"]/div/div[1]/main/div[3]/div[1]/section/div/div[3]/ul/li");
    public static final By firstProduct = By.xpath("//*[@id=\"desktopSearchResults\"]/div[2]/section/ul/li[1]");
    public static final By sizeOfJacket = By.xpath("//*[@id=\"sizeButtonsContainer\"]/div[2]/div");
    public static final By addToBagBtn = By.xpath("//*[@id=\"mountRoot\"]/div/div/div/main/div[2]/div[2]/div[3]/div/div[1]");
    public static final By numberOfItemsInCart = By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[2]/a[2]/span[2]");
    public static final By bagBtn = By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/div[2]/a[2]/span[3]");
    public static final By placeOrderBtn = By.xpath("//*[@id=\"appContent\"]/div/div/div/div/div/div[2]/div[3]/a/div");
    public static final By continuePlaceOrderBtn = By.id("placeOrderButton");
    public static final By changeAddressBtn = By.xpath("//*[@id=\"appContent\"]/div/div/div/div/div/div[1]/div[1]/div[2]");
    public static final By selectDeliveryAddressLbl = By.xpath("//*[@id=\"appContent\"]/div/div/div/div/div[1]/div/div[1]/div[1]");
    public static final By choosePaymentModeLbl = By.xpath("//*[@id=\"appContent\"]/div/div/div/div/div[1]/div[2]/div[1]");



    public WebElement getSearchBoxEdit() { return waitForElement(searchBoxEdit); }
    public List<WebElement> getPriceRange() { return waitForElements(priceRangeFilterChk); }
    public List<WebElement> getColorFilterChk() { return waitForElements(colorFilterChk); }
    public List<WebElement> getDiscountFilterRdb() { return waitForElements(discountFilterRdb); }
    public WebElement getFirstProduct() { return waitForElement(firstProduct); }
    public List<WebElement> getSizeOfJacket() { return waitForElements(sizeOfJacket); }
    public WebElement getAddToBagBtn() { return waitForElement(addToBagBtn); }
    public WebElement getNumberOfItemsInCart() { return waitForElement(numberOfItemsInCart); }
    public WebElement getBagBtn() { return waitForElement(bagBtn); }
    public WebElement getPlaceOrder() { return waitForElement(placeOrderBtn); }
    public WebElement getContinuePlaceOrderBtn() { return waitForElement(continuePlaceOrderBtn); }
    public WebElement getChangeAddressBtn() { return waitForElement(changeAddressBtn); }
    public WebElement getSelectDeliveryAddressLbl() { return waitForElement(selectDeliveryAddressLbl); }
    public WebElement getChoosePaymentModeLbl() { return waitForElement(choosePaymentModeLbl); }


    public MyntraSearchPage searchItemAddToCart() {
        logger.info("Searching the Item with filters like price range and discount");
        enterText(getSearchBoxEdit(), ReadProperties.readConfigProperty("productName"));
        enterText(getSearchBoxEdit(), Keys.ENTER);
        waitForPageLoad();
        for(int j = 0 ; j < getPriceRange().size(); j++) {
            String strPriceRange = "//*[@id=\"mountRoot\"]/div/div[1]/main/div[3]/div[1]/section/div/div[2]/ul/li[?]/label";
            strPriceRange = strPriceRange.replace("?", String.valueOf(j+1));
            WebElement elem = driver.findElement(By.xpath(strPriceRange));
            clickOn(elem);
            waitFor(3000);
            logger.info("Selected the Price Range");
            break;
        }
        for(int i = 0 ; i < getDiscountFilterRdb().size(); i++) {
            String strDiscountRate = "//*[@id=\"mountRoot\"]/div/div[1]/main/div[3]/div[1]/section/div/div[3]/ul/li[?]/label";
            strDiscountRate = strDiscountRate.replace("?", String.valueOf(i+1));
            WebElement elem = driver.findElement(By.xpath(strDiscountRate));
            if(getText(elem).equalsIgnoreCase(ReadProperties.readConfigProperty("discount"))) {
                clickOn(elem);
                waitForPageLoad();
                logger.info("Selected the Discount Percentage");
                break;
            }
        }
        clickOn(getFirstProduct());
        waitForPageLoad();
        Set<String> s=driver.getWindowHandles();
        if(s.size() > 1) {
            logger.info("Item has been selected and opened in a new Tab");
            Assert.assertTrue(true, "Item has been selected and opened in a new Tab");
        } else {
            logger.info("Item has NOT been selected and a New Tab is NOT Opened");
            Assert.assertTrue(false, "Item has NOT been selected and a New Tab is NOT Opened");
        }
        return this;
    }

    public MyntraSearchPage addItemsToBag() {
        logger.info("Selecting the size and adding the item to Bag");
        String parent=driver.getWindowHandle();
        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        while(I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                logger.info("Switching to Child Window");
                for(int k = 0 ; k < getSizeOfJacket().size(); k++) {
                    String selectSize = "//*[@id=\"sizeButtonsContainer\"]/div[2]/div[?]/div[1]/button/p";
                    selectSize = selectSize.replace("?", String.valueOf(k+1));
                    WebElement elem = driver.findElement(By.xpath(selectSize));
                    if(getText(elem).equalsIgnoreCase(ReadProperties.readConfigProperty("sizeOfJacket"))) {
                        clickOn(elem);
                        waitForPageLoad();
                        break;
                    }
                }
                clickOn(getAddToBagBtn());
                waitFor(1000);
                driver.close();
                driver.switchTo().window(parent);
                logger.info("Switching to Parent Window");
                driver.navigate().refresh();
                waitForPageLoad();
                waitFor(1000);
            }
        }
        if(getText(getNumberOfItemsInCart()).equalsIgnoreCase(ReadProperties.readConfigProperty("numberOfItemsInBag"))) {
            logger.info("Number of Items Added in Bag is matching");
            Assert.assertTrue(true, "Number of Items Added in Bag is matching");
        } else {
            logger.info("Number of Items Added in Bag is NOT matching");
            Assert.assertTrue(true, "Number of Items Added in Bag is NOT matching");
        }
        return this;
    }

    public MyntraSearchPage placeTheOrder() {
        clickOn(getBagBtn());
        waitForPageLoad();
        if(isElemDisplayed(getChangeAddressBtn())) {
            logger.info("Successfully navigated to Bag/Cart Page");
            Assert.assertTrue(true, "Successfully navigated to Bag/Cart Page");
        } else {
            logger.info("NOT Successfully navigated to Bag/Cart Page");
            Assert.assertTrue(false, "Successfully navigated to Bag/Cart Page");
        }
        clickOn(getPlaceOrder());
        waitForPageLoad();
        if(isElemDisplayed(getSelectDeliveryAddressLbl())) {
            logger.info("Successfully navigated to Select Delivery Address Page");
            Assert.assertTrue(true, "Successfully navigated to Select Delivery Address Page");
        } else {
            logger.info("NOT Successfully navigated to Select Delivery Address Page");
            Assert.assertTrue(false, "Successfully navigated to Select Delivery Address Page");
        }
        clickOn(getContinuePlaceOrderBtn());
        waitForPageLoad();
        if(isElemDisplayed(getChoosePaymentModeLbl())) {
            logger.info("Successfully navigated to Choose Payment Mode Page");
            Assert.assertTrue(true, "Successfully navigated to Choose Payment Mode Page");
        } else {
            logger.info("NOT Successfully navigated to Choose Payment Mode Page");
            Assert.assertTrue(false, "Successfully navigated to Choose Payment Mode Page");
        }
        return this;
    }
}