package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import ge.tbc.testautomation.steps.ProductSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;

import java.util.Arrays;

@Test(description = "Allure Pom Tests")
@Listeners({AllureTestNg.class})
public class AllurePOMTests {
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

    @Test(description = "[SQDTBC-T2727] Validate Search Bar Functionality")
    @Description("Search for an item and validate the number of results")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://fake-jira.com/testcase/SQDTBC-T2727")
    public void testPOM() {
        productSteps
                .searchForItem("Pliers")
                .validateNumberOfCards(4);
    }

    @Test(description = "[SQDTBC-T2727] Validate Search Bar Functionality")
    @Description("Search for an item and validate the number of results")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://fake-jira.com/testcase/SQDTBC-T2727")
    public void testPOM2() {
        productSteps
                .searchForItem("Pliers")
                .validateNumberOfCards(4);
    }
}
