package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ProductsPage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductSteps {
    Page page;
    ProductsPage productsPage;

    public ProductSteps(Page page) {
        this.page = page;
        this.productsPage = new ProductsPage(page);
    }

    @Step("Searching for {} using the search bar")
    public ProductSteps searchForItem(String item){
        productsPage.searchBar.fill(item);
        productsPage.searchButton.click();

        return this;
    }

    @Step("Validating number of cards to be {}")
    public ProductSteps validateNumberOfCards(int number){
        page.waitForSelector(".card");
        assertThat(productsPage.cards).hasCount(number);

        return this;
    }
}
