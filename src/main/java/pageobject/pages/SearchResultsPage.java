package pageobject.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {
    private final ElementsCollection searchResults = $$(By.xpath("//*[@data-component-type=\"s-search-result\"]"));
    private SelenideElement whole = $(By.className("a-price-whole"));
    private SelenideElement fraction = $(By.className("a-price-fraction"));
    private SelenideElement low_price = $(By.xpath("//*[@id=\"low-price\"]"));
    private SelenideElement high_price = $(By.xpath("//*[@id=\"high-price\"]"));
    private SelenideElement submitForm = $(By.xpath("//*[@id=\"priceRefinements\"]//form"));
    private SelenideElement dropDownPrompt = $(By.xpath("//*[@class=\"a-dropdown-prompt\"]"));
    private SelenideElement sort_select = $(By.xpath("//*[@id=\"s-result-sort-select_1\"]"));

    public ElementsCollection getResults() {
        return searchResults;
    }

    public String getBrand(SelenideElement element) {
        return element.$(By.tagName("h2")).getText().toLowerCase();
    }

    public void filterByPrice() {
        low_price.click();
        high_price.click();
        submitForm.submit();
    }

    public void sortingByLowToHighPrice() {
        dropDownPrompt.click();
        sort_select.click();
    }

    public void wholePrice() {
        for (SelenideElement element : searchResults) {
            whole = element;
            fraction = element;
            if (!whole.exists() || !fraction.exists()) {
                continue;
            }
            String result = whole.getText() + "." + fraction.getText();
            double price = Double.parseDouble(result);
        }
    }
}
