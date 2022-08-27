package pageobject.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelectedCategoryPage {
    private SelenideElement selectedElement = $(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/div/div/div/div/div"));

    public SelectedElementPage openSelectedElementPage() {
        selectedElement.click();
        return new SelectedElementPage();
    }
}
