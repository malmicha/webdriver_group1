import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class FilterAndSearch {
    WebDriver webDriver;

    @BeforeMethod
    public void SetUpDriverAndOpenWebpage() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }
        webDriver.get("https://www.amazon.com/s?k=gaming+chairs");
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void TearDown() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void BrandsElementInTitle() {
        WebElement brandLi = webDriver.findElement(By.xpath("(//*[@id=\"brandsRefinements\"]//li)[1]"));
        String brandName = brandLi.getAttribute("aria-label").toLowerCase();
        WebElement brandInput = brandLi.findElement(By.tagName("input"));
        brandInput.sendKeys(Keys.SPACE);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> searchResults = webDriver.findElements(By.xpath("//*[@data-component-type=\"s-search-result\"]"));
        for (WebElement element : searchResults) {
            WebElement h2 = element.findElement(By.tagName("h2"));
            assert h2.getText().toLowerCase().startsWith(brandName);
        }
    }

    @Test
    public void PriceVerify() {
        WebElement lowPrice = webDriver.findElement(By.xpath("//*[@id=\"low-price\"]"));
        lowPrice.sendKeys("100");
        WebElement highPrice = webDriver.findElement(By.xpath("//*[@id=\"high-price\"]"));
        highPrice.sendKeys("150");
        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"priceRefinements\"]//form"));
        submitButton.submit();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> searchResults = webDriver.findElements(By.xpath("//*[@data-component-type=\"s-search-result\"]"));
        for (WebElement element : searchResults) {
            try {
                WebElement whole = element.findElement(By.className("a-price-whole"));
                WebElement fraction = element.findElement(By.className("a-price-fraction"));
                String result = whole.getText() + "." + fraction.getText();
                double price = Double.parseDouble(result);
                assert price >= 100.00;
                assert price <= 150.00;
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void PriceSorting() {
        WebElement DropdownPrompt = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"a-dropdown-prompt\"]")));
        DropdownPrompt.click();
        WebElement lowToHigh = webDriver.findElement(By.xpath("//*[@id=\"s-result-sort-select_1\"]"));
        lowToHigh.click();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> searchResults = webDriver.findElements(By.xpath("//*[@data-component-type=\"s-search-result\"]"));
        double price = 0.00;
        for (WebElement element : searchResults) {
            try {
                WebElement whole = element.findElement(By.className("a-price-whole"));
                WebElement fraction = element.findElement(By.className("a-price-fraction"));
                String result = whole.getText() + "." + fraction.getText();
                double currentPrice = Double.parseDouble(result);
                assert currentPrice >= price;
                price = currentPrice;
            } catch (Exception e) {
            }
        }
    }
}