package ge.tbc.testautomation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorTests extends BaseTest {
    @Test
    public void testGetByRole() {
        Locator hammerCheckbox = page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Hammer"));
//        PlaywrightAssertions.assertThat(hammerCheckbox).isChecked();
        PlaywrightAssertions.assertThat(hammerCheckbox).isVisible();

        Locator searchButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Search"));
        PlaywrightAssertions.assertThat(searchButton).isVisible();
    }

    @Test
    public void testChaining() {
        Locator screwDriverCheckbox = page
                .locator("div.checkbox")
                .getByLabel("Screwdriver");
        PlaywrightAssertions.assertThat(screwDriverCheckbox).not().isChecked();
    }

    @Test
    public void testLists() {
        Locator allCheckboxDivs = page
                .locator("div.checkbox")
                .locator("ul")
                .locator("div");

        Locator chiselCheckBoxDiv = allCheckboxDivs
                .filter(new Locator.FilterOptions()
                        .setHas(page.getByText("Chisels")));

//        System.out.println(chiselCheckBoxDiv.count());
        for (int i = 0; i < allCheckboxDivs.count(); i++){
            allCheckboxDivs.nth(i).locator("input").check();
        }
//        chiselCheckBoxDiv.locator("input").scrollIntoViewIfNeeded();
//        chiselCheckBoxDiv.check();

//        USING STREAMS AND MAP
//        for (Locator checkbox:
//                allCheckboxDivs.all()) {
//            checkbox.locator("input").check();
//        }
//
//        List<Locator> inputs = allCheckboxDivs.all()
//                .stream()
//                .map(div -> div.locator("input"))
//                .toList();
//        for (Locator input:
//                inputs) {
//            input.check();
//        }
    }

    @Test(description = "Get all cards on dashboard and check if each has a dollar sign in the pricing section")
    public void cardPricesShouldContainDollarSignsTest() {
        Page page = browserContext.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        Locator allCards = page.locator(".card");
        for (Locator card :
                allCards.all()) {
            PlaywrightAssertions.assertThat(card.locator(".card-footer")).containsText("$");
        }
    }
}
