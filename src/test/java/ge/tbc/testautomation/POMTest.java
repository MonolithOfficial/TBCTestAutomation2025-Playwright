package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.ProductSteps;
import org.testng.annotations.*;

import java.util.Arrays;

public class POMTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    ProductSteps productSteps;

    @BeforeClass
    public void setUp(){
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(false);
        browser = playwright.chromium().launch(launchOptions);

//        IF SAME CONTEXT
//        browserContext = browser.newContext();
//        page = browserContext.newPage();
//        productSteps = new ProductSteps(page);
//        page.navigate("https://practicesoftwaretesting.com/");
    }

    @AfterClass
    public void tearDown(){
//        IF SAME CONTEXT
//        browserContext.close();
        browser.close();
        playwright.close();
    }

    @BeforeMethod
    public void resetContext(){
        browserContext = browser.newContext();
        page = browserContext.newPage();
        productSteps = new ProductSteps(page);
        page.navigate("https://practicesoftwaretesting.com/");
    }

    @AfterMethod
    public void closeContext(){
        browserContext.close();
    }

    @Test
    public void testPOM() {
        productSteps
                .searchForItem("Pliers")
                .validateNumberOfCards(10);
    }

    @Test
    public void testPOM2() {
        productSteps
                .searchForItem("Pliers")
                .validateNumberOfCards(10);
    }
}
