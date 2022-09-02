import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.pages.GamingChairsPage;
import pageobject.pages.SearchResultsPage;

public class FilterAndSearchTests {

    @BeforeMethod
    public void OpenWebpage() {
        Selenide.open("https://www.amazon.com/s?k=gaming+chairs");
    }

    @Test
    public void BrandsElementInTitle() {
        GamingChairsPage gamingChairsPage = new GamingChairsPage();
        gamingChairsPage.filterByBrand();
        String brandName = gamingChairsPage.findBrand();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        ElementsCollection searchResults = searchResultsPage.getResults();

        for (SelenideElement element : searchResults) {
            Assert.assertTrue(searchResultsPage.getBrand(element).startsWith(brandName));
        }
    }

    @Test
    public void PriceVerify() {
        GamingChairsPage gamingChairsPage = new GamingChairsPage();
        gamingChairsPage.sortingByLowToHighPrice();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        ElementsCollection searchResults = searchResultsPage.getResults();

        for (SelenideElement element : searchResults) {
            Double price = searchResultsPage.wholePrice(element);
            if (price != null) {
                double delta = 0.01;
                Assert.assertTrue(price >= 100.00 - delta);
                Assert.assertTrue(price <= 150.00 + delta);
            }
        }
    }

    @Test
    public void PriceSorting() {
        GamingChairsPage gamingChairsPage = new GamingChairsPage();
        gamingChairsPage.sortingByLowToHighPrice();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        ElementsCollection searchResults = searchResultsPage.getResults();
        double price = 0.00;
        for (SelenideElement element : searchResults) {
            Double currentPrice = searchResultsPage.wholePrice(element);
            if (currentPrice != null) {
                Assert.assertTrue(currentPrice >= price);
                price = currentPrice;
            }
        }
    }
}