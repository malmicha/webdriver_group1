package pageobject.pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;
import pageobject.modules.DeliverToPopupModule;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement deliverToButton = $(By.id("glow-ingress-line2"));
    private SelenideElement deliverToValue = $(By.id("glow-ingress-line2"));
    private SelenideElement selectedCategoryButton = $(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div[5]/div/div[2]/div[1]/div[1]/a"));


    public HomePage open() {
        Selenide.open("https://amazon.com/");
        return this;
    }

    public DeliverToPopupModule openDeliverToPopupModule() {
        deliverToButton.click();
        return new DeliverToPopupModule();
    }
    public String getDeliverToText() {
        return deliverToValue.shouldBe(Condition.visible).text();
    }
    public SelectedCategoryPage openSelectedCategoryPage() {
        selectedCategoryButton.click();
        return new SelectedCategoryPage();
    }




}
