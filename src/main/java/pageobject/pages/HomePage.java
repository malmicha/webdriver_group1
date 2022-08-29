package pageobject.pages;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobject.modules.DeliverToPopupModule;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private SelenideElement deliverToButton = $(By.id("glow-ingress-line2"));
    private SelenideElement deliverToValue = $(By.id("glow-ingress-line2"));
    private SelenideElement selectedCategoryButton = $(By.xpath("//a[@aria-label = \"Notebooks\"]"));

    public HomePage open() {
        Selenide.open("https://amazon.com/");
        return this;
    }

    public DeliverToPopupModule openDeliverToPopupModule() {
        deliverToButton.click();
        return new DeliverToPopupModule();
    }

    public String getDeliverToText() {
        return deliverToValue.shouldBe(visible).text();
    }

    public SelectedCategoryPage openSelectedCategoryPage() {
        Selenide.sleep(3000);
        selectedCategoryButton.should(exist, Duration.ofSeconds(10)).shouldBe(visible).click();
        return new SelectedCategoryPage();
    }
}
