package myntraTests;

import java.lang.reflect.Method;

import utils.extentReportsUtils.ExtentTestManager;
import org.testng.annotations.Test;


public class MyntraTest extends MyntraBaseTest {

    @Test(priority=0, description="Navigate to Myntra Web Application")
    public void myntraLaunchWebAppln(Method method) {
        ExtentTestManager.startTest(method.getName(), "Navigate to Myntra Web Application");
        myntraHomePage
                .navigateToMyntraWebAppln();
//                .navigateToLoginPage();
    }

    @Test(priority = 1, description = "Login to Myntra Web Application with Mobile Number & Password")
    public void myntraLogin(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login to Myntra Web Application with Mobile Number & Password");
        myntraLoginPage
                .loginToMyntraWithPassword();
    }

    @Test(priority = 2, description = "Searching an Item and applying filters")
    public void myntraSearchItem(Method method) {
        ExtentTestManager.startTest(method.getName(), "Searching an Item and applying filters");
        myntraSearchPage
                .searchItemAddToCart();
    }

    @Test(priority = 3, description = "Adding an Item to cart")
    public void myntraAddItemsToBag(Method method) {
        ExtentTestManager.startTest(method.getName(), "Adding an Item to cart");
        myntraSearchPage
                .addItemsToBag();
    }

    @Test(priority = 4, description = "Adding an Item to cart")
    public void myntraPlaceTheOrder(Method method) {
        ExtentTestManager.startTest(method.getName(), "Adding an Item to cart");
        myntraSearchPage
                .placeTheOrder();
    }
}
