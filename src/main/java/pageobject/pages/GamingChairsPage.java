package pageobject.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class GamingChairsPage {
    private final SelenideElement brandLi = $(By.xpath("(//*[@id=\"brandsRefinements\"]//li)[2]"));
    private final SelenideElement brandInput = brandLi.$(By.tagName("input"));

    public void filterByBrand() {
        brandInput.sendKeys(Keys.SPACE);
    }

    public String findBrand() {
        String label = brandLi.getAttribute("aria-label");
        return label != null ? label.toLowerCase() : "";
    }
}

