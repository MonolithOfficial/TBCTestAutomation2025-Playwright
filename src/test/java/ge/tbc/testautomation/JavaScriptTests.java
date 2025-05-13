package ge.tbc.testautomation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JavaScriptTests {
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
    public void testDocumentReadyState() {
        page.navigate("https://swoop.ge");
//        page.waitForTimeout(50000);

        page.waitForFunction("() => document.readyState === 'complete'");
        String readyState = (String) page.evaluate("document.readyState");
        System.out.println(readyState);
    }

    @Test
    public void testJavaScript() {
        page.navigate("https://the-internet.herokuapp.com/dynamic_controls");
        Locator enableButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Enable"));
        Locator removeButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove"));
        enableButton.evaluate("element => element.scrollIntoView();");

        Map<String, ElementHandle> buttons = new HashMap<>();
        buttons.put("button1", enableButton.elementHandle());
        buttons.put("button2", removeButton.elementHandle());

        page.evaluate("({ button1, button2 }) => {button1.scrollIntoView(); button2.scrollIntoView();}", buttons);
    }
}
