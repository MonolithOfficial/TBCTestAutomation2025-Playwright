package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.ProductSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ParallelCrossBrowserTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    ProductSteps productSteps;

    @BeforeClass
    @Parameters({"browserType"})
    public void setUp(String browserType){
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(false);

        if (browserType.equalsIgnoreCase("chromium")){
            browser = playwright.chromium().launch(launchOptions);
        } else if (browserType.equalsIgnoreCase("safari")) {
            browser = playwright.webkit().launch(launchOptions);
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        productSteps = new ProductSteps(page);
        page.navigate("https://practicesoftwaretesting.com/");
    }

    @AfterClass
    public void tearDown(){
        browserContext.close();
        browser.close();
        playwright.close();
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
