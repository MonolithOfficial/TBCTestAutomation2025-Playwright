package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AlertsFramesTabs {
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
        launchOptions.setSlowMo(1000);
        browser = playwright.chromium().launch(launchOptions);
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterClass
    public void tearDown(){
        browserContext.close();
        browser.close();
        playwright.close();
    }

    @Test
    public void testAlert() {
        page.navigate("https://demoqa.com/alerts");
        Locator alertButton = page.locator("#alertButton");
        alertButton.click();

        page.onDialog(Dialog::accept);
//        page.onDialog(Dialog::dismiss);
//        page.onDialog(Dialog::message);
    }

    @Test
    public void testFrames() {
        page.navigate("https://the-internet.herokuapp.com/nested_frames");

        Locator leftText = page
                .frameLocator("[name='frame-top']")
                .frameLocator("[name='frame-left']")
                .getByText("LEFT");
    }

    @Test
    public void testTabs() {
        Page page1 = browserContext.newPage();
        page1.navigate("https://google.com");

        Page page2 = browserContext.newPage();
        page2.navigate("https://stackoverflow.com");

        page1.bringToFront();
        List<Page> allTabs = browserContext.pages();

        allTabs.forEach(tab -> System.out.println(tab.title()));
    }
}
