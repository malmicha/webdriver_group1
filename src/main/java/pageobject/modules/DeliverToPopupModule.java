package pageobject.modules;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageobject.pages.HomePage;
import pageobject.pages.SelectedCategoryPage;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DeliverToPopupModule {
    private SelenideElement deliverToButton = $(By.id("glow-ingress-line2"));

    private SelenideElement popupInput = $(By.id("GLUXZipUpdateInput")).shouldBe(visible);
    private SelenideElement popupApplyButton = $(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span/input"));
    private SelenideElement popupContinueButton = $(By.xpath("/html/body/div[5]/div/div/div[2]/span/span"));
    private SelenideElement doneButton = $(By.name("glowDoneButton"));
    private SelenideElement popupListOfCountriesDropdown = $(By.xpath("//*[@id=\"GLUXSpecifyLocationDiv\"]/div[4]"));
    private SelenideElement canadaSelectButton = $(By.id("GLUXCountryList_1"));
    private SelenideElement listOfCountriesWrapper = $(By.className("a-list-link"));
    private List<SelenideElement> listOfCountries = $$(By.className("a-dropdown-link"));

    public DeliverToPopupModule openDeliverToPopupModule() {
        deliverToButton.click();
        return new DeliverToPopupModule();
    }

    public List<SelenideElement> getListOfCountries() {
        this.popupListOfCountriesDropdown.hover().click();
        this.listOfCountriesWrapper.shouldBe(exist, Duration.ofSeconds(10)).shouldBe(visible);
        return listOfCountries;
    }

    public HomePage insertIndex(String index) {
        this.popupInput.click();
        this.popupInput.val(index);
        this.popupApplyButton.click();
        this.popupContinueButton.shouldBe(exist, Duration.ofSeconds(10)).shouldBe(visible).click();
        return new HomePage();
    }

    public HomePage selectCanada() {
        this.popupListOfCountriesDropdown.should(exist, Duration.ofSeconds(10)).hover().click();
        this.canadaSelectButton.should(exist, Duration.ofSeconds(15)).shouldBe(visible).click();
        this.doneButton.should(exist, Duration.ofSeconds(10)).shouldBe(visible).click();
        return new HomePage();
    }
}
