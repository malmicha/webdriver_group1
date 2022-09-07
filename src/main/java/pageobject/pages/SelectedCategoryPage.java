package pageobject.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SelectedCategoryPage {
    private SelenideElement selectedElement = $(By.xpath("//img[@data-image-source-density = \"1\"]"));

    public SelectedElementPage openSelectedElementPage() {
        selectedElement.click();
        return new SelectedElementPage();
    }
}
