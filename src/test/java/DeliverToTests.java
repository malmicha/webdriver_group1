import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.pages.HomePage;

public class DeliverToTests {

    @AfterMethod
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }

    //training to use Data providers
    @DataProvider(name = "destinationIndex")
    public Object[][] destinationIndex() {
        return new Object[][]{
                {"36104", "Montgomery"},
        };
    }

    @Test(dataProvider = "destinationIndex")
    public void selenideTest1(String index, String destination) {
        String deliverToText = new HomePage().open()
                .openDeliverToPopupModule().insertIndex(index).getDeliverToText();
        Assert.assertTrue(deliverToText.contains(destination), "Delivery destination should change");
    }

    @Test
    public void selenideTest2() {
        String selectedCountry = "Poland";
        boolean isCountryExist = new HomePage().open()
                .openDeliverToPopupModule().getListOfCountries().stream()
                .anyMatch(country -> country.equals(selectedCountry));
        Assert.assertTrue(isCountryExist, "Selected country does not exist");
    }

    @Test
    public void selenideTest3() {
        String selectedCountry = "Canada";
        String shippingToText = new HomePage().open()
                .openDeliverToPopupModule().selectCountry(selectedCountry).openSelectedCategoryPage()
                .openSelectedElementPage().getShippingDetailsText();
        Assert.assertTrue(shippingToText.contains(selectedCountry), "Delivery destination is not chosen");
    }
}
