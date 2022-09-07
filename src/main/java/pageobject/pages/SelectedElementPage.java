package pageobject.pages;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class SelectedElementPage {
    private SelenideElement shippingDetails = $(By.xpath("//*[@id=\"contextualIngressPtLabel_deliveryShortLine\"]/span[2]"));
    public String getShippingDetailsText() {
        return shippingDetails.text();
    }
}
