package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductsPage {
    public Locator searchBar;
    public Locator hammerCheckBox;
    public Locator pagination;
    public Locator searchButton;
    public Locator cards;

    public ProductsPage(Page page) {
        this.searchBar = page.locator("#search-query");
        this.hammerCheckBox = page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Hammer"));
        this.pagination = page.locator("ul.pagination");
        this.searchButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Search"));
        this.cards = page.locator(".card");
    }


}
