package pageobject.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class GamingChairsPage {
    private final SelenideElement brandLi = $(By.xpath("(//*[@id=\"brandsRefinements\"]//li)[2]"));
    private final SelenideElement brandInput = brandLi.$(By.tagName("input"));
    private SelenideElement low_price = $(By.xpath("//*[@id=\"low-price\"]"));
    private SelenideElement high_price = $(By.xpath("//*[@id=\"high-price\"]"));
    private SelenideElement submitForm = $(By.xpath("//*[@id=\"priceRefinements\"]//form"));

    public void filterByBrand() {
        brandInput.sendKeys(Keys.SPACE);
    }

    public String findBrand() {
        String label = brandLi.getAttribute("aria-label");
        return label != null ? label.toLowerCase() : "";
    }

    public void filterByPrice(double low, double high) {
        low_price.sendKeys(String.valueOf(low));
        high_price.sendKeys(String.valueOf(high));
        submitForm.submit();
    }
}

