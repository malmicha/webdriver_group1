import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.pages.HomePage;


public class DeliverToTests {
    @AfterMethod
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }
    @Test
    public void selenideTest1() {
        String deliverToText = new HomePage().open().openDeliverToPopupModule().insertIndex("36104").getDeliverToText();
        System.out.println(deliverToText);
    }

    @Test
    public void selenideTest2() {
        boolean isPolandExist = new HomePage().open().openDeliverToPopupModule().getListOfCountries().stream().anyMatch(country -> country.getText().contains("Poland"));
        Assert.assertTrue(isPolandExist);
    }

    @Test
    public void selenideTest3() {
        String shippintToText = new HomePage().open()
                .openDeliverToPopupModule().selectCanada().openSelectedCategoryPage()
                .openSelectedElementPage().getShippingDetailsText();
        Assert.assertTrue(shippintToText.contains("Canada"));
    }


}
