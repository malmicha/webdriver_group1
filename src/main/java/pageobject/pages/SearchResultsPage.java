package pageobject.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {
    private final ElementsCollection searchResults = $$(By.xpath("//*[@data-component-type=\"s-search-result\"]"));
    private SelenideElement dropDownPrompt = $(By.xpath("//*[@class=\"a-dropdown-prompt\"]"));
    private SelenideElement sort_select = $(By.xpath("//*[@id=\"s-result-sort-select_1\"]"));

    public ElementsCollection getResults() {
        return searchResults;
    }

    public String getBrand(SelenideElement element) {
        return element.$(By.tagName("h2")).getText().toLowerCase();
    }

    public Double wholePrice(SelenideElement element) {
        SelenideElement whole = element.$(By.className("a-price-whole"));
        SelenideElement fraction = element.$(By.className("a-price-fraction"));
        if (!whole.exists() || !fraction.exists()) {
            return null;
        }
        String result = whole.getText() + "." + fraction.getText();
        return Double.parseDouble(result);
    }

    public void sortingByLowToHighPrice() {
        dropDownPrompt.click();
        sort_select.click();
    }


}
