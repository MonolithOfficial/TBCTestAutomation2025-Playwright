package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WaitsTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeClass
    public void setUp(){
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(false);
//        launchOptions.setSlowMo(1000);
        browser = playwright.chromium().launch(launchOptions);
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }
    @Test
    public void testAutoWaits() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        Locator enableButton = page.getByRole(AriaRole.BUTTON).getByText("Enable");
        enableButton.click();
        Locator disableButton = page.getByRole(AriaRole.BUTTON).getByText("Disable");
        disableButton.click();
    }

    @Test
    public void testExplicitWaitTest() {
        page.navigate("https://practicesoftwaretesting.com/");
        page.waitForSelector("[data-test='product-name']");
        List<String> productNames = page.locator("[data-test='product-name']").allInnerTexts();
//        Locator productNamesLocator = page.locator("[data-test='product-name']");
//        productNamesLocator.first().waitFor();
//        List<String> productNames = productNamesLocator.allInnerTexts();
        Assert.assertTrue(productNames.containsAll(List.of("Pliers", "Bolt Cutters")));
    }

    @Test
    public void testEnableDisableButtonWithExplicitWait() {
        Page page = browserContext.newPage();
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        Locator enableButton = page.locator("button").getByText("Enable");
        enableButton.click();
        Locator disableButton = page.locator("button").getByText("Disable");
        disableButton.click();
        page.waitForSelector("img[src='/img/ajax-loader.gif']", new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN).setTimeout(10000));
    }

    @Test
    public void testEnableDisableButtonWithAssertionWait() {
        Page page = browserContext.newPage();
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        Locator enableButton = page.locator("button").getByText("Enable");
        enableButton.click();
        Locator disableButton = page.locator("button").getByText("Disable");
        disableButton.click();
        PlaywrightAssertions.assertThat(page.locator("img[src='/img/ajax-loader.gif']").first()).not().isVisible();
    }
}
