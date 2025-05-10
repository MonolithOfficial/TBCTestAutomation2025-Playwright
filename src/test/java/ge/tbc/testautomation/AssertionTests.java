package ge.tbc.testautomation;

import com.microsoft.playwright.Locator;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AssertionTests extends BaseTest{
    @Test
    public void testPageAssertion() {
        assertThat(page).hasTitle("Practice Software Testing - Toolshop - v5.0");
    }

    @Test
    public void testLocatorAssert() {
        assertThat(page.locator("a.navbar-brand > svg")).isVisible();
    }

    @Test(description = "check if list of checkboxes contains Hammer and Wrench entries")
    public void testListAssertion() {
        Locator allCheckBoxDivs = page.locator("//div[@class='checkbox']//ul//div");
        assertThat(allCheckBoxDivs).containsText(new String[]{"Hammer", "Wrench"});
    }
}
