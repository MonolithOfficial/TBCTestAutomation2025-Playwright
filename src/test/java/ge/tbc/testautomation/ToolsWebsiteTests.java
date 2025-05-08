package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ToolsWebsiteTests {
    Playwright playwright;
    Browser browser;
//    BrowserContext browserContext;

    @BeforeClass
    public void setUp(){
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setArgs(Arrays.asList("--disable-gpu", "--disable-extensions", "--start-maximized"));
        launchOptions.setHeadless(false);
        launchOptions.setSlowMo(2000);
//        launchOptions.
        browser = playwright.chromium().launch(launchOptions);
//        browserContext = browser.newContext();
    }

    @AfterClass
    public void tearDown(){
//        browserContext.close();
        browser.close();
        playwright.close();
    }

    @Test
    public void checkIfThorHammerExists() {
        Page page = browser.newContext(new Browser.NewContextOptions().setViewportSize(null)).newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        System.out.println(browser.contexts());
        Locator hammerCheckBox = page.getByLabel("Hammer");
//        Locator hammerCheckBox = page.locator("//label[contains(text(), 'Hammer')]//input");
        hammerCheckBox.click();
        Locator thorHammer = page.getByAltText("Thor Hammer");
        Assert.assertEquals(thorHammer.count(), 1);
//        thorHammer.click();
//        System.out.println(hammerCheckBox.allInnerTexts());
    }

    @Test
    public void checkIfThorHammerExists2() {
        Page page = browser.newContext(new Browser.NewContextOptions().setViewportSize(null))   .newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        System.out.println(browser.contexts());
        Locator hammerCheckBox = page.getByLabel("Hammer");
//        Locator hammerCheckBox = page.locator("//label[contains(text(), 'Hammer')]//input");
        hammerCheckBox.click();
        Locator thorHammer = page.getByAltText("Thor Hammer");
        Assert.assertEquals(thorHammer.count(), 1);
//        thorHammer.click();
//        System.out.println(hammerCheckBox.allInnerTexts());
    }
}
