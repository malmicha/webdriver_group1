
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;


public class DeliverToTests {

    WebDriver webDriver;
    WebElement deliverToButton;
    WebElement popupListOfCountriesDropdown;


    @BeforeMethod
    public void openAmazonPage() {
        if (webDriver == null) {
            System.setProperty(
                    "webdriver.chrome.driver",
                    "C:\\Users\\stass\\IdeaProjects\\webdriver_group1\\src\\test\\resources\\webdriver\\chromedriver.exe"
            );
            webDriver = new ChromeDriver();
        }
        webDriver.get("https://amazon.com/");
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void quitSession() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void deliverTo_test1() {

        WebElement deliverToButton = webDriver.findElement(By.id("glow-ingress-line2"));
        deliverToButton.click();

        WebElement popupInput = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXZipUpdateInput")));
        popupInput.click();
        popupInput.sendKeys("36104");
        WebElement popupApplyButton = webDriver.findElement(By.xpath("//*[@id=\"GLUXZipUpdate\"]/span/input"));
        popupApplyButton.click();
        WebElement popupContinueButton = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div/div/div[2]/span/span")));
        popupContinueButton.click();

        webDriver.get("https://amazon.com/");

        WebElement deliverToValue = webDriver.findElement(By.id("glow-ingress-line2"));
        Assert.assertTrue(deliverToValue.getText().contains("Montgomery"));
    }

    @Test
    public void deliverTo_test2() {

        WebElement deliverToButton = webDriver.findElement(By.id("glow-ingress-line2"));
        deliverToButton.click();

        WebElement popupListOfCountriesDropdown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"GLUXSpecifyLocationDiv\"]/div[4]")));
        popupListOfCountriesDropdown.click();
        List<WebElement> listOfCountries = webDriver.findElements(By.className("a-dropdown-link"));
        boolean isPolandExist = listOfCountries.stream().anyMatch(country -> country.getText().contains("Poland"));
        Assert.assertTrue(isPolandExist);

    }

    @Test
    public void deliverTo_test3() {

        WebElement deliverToButton = webDriver.findElement(By.id("glow-ingress-line2"));
        deliverToButton.click();

        WebElement popupListOfCountriesDropdown = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"GLUXSpecifyLocationDiv\"]/div[4]")));
        popupListOfCountriesDropdown.click();
        WebElement canadaSelectButton = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXCountryList_1")));
        canadaSelectButton.click();
        WebElement doneButton = webDriver.findElement(By.name("glowDoneButton"));
        doneButton.click();

        webDriver.get("https://amazon.com/");

        WebElement notebooksCategoryButton = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div[5]/div/div[2]/div[1]/div[1]/a"));
        notebooksCategoryButton.click();

        WebElement selectedElementFromCategory = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/div/div/div/div/div"));
        selectedElementFromCategory.click();
        WebElement shippingDetails = webDriver.findElement(By.xpath("//*[@id=\"contextualIngressPtLabel_deliveryShortLine\"]/span[2]"));
        Assert.assertTrue(shippingDetails.getText().contains("Canada"));

    }


}
