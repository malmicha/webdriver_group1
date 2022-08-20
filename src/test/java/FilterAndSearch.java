import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;


public class FilterAndSearch {
    WebDriver webDriver;

    @BeforeMethod
    public void SetUpDriver() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }
    }

    @AfterTest
    public void TearDown() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void BrandsElementInTitle() {
        webDriver.get("https://www.amazon.com/s?k=gaming+chairs");
        webDriver.manage().window().maximize();

        WebElement brandLi = webDriver.findElement(By.xpath("(//*[@id=\"brandsRefinements\"]//li)[1]"));
        String brandName = brandLi.getAttribute("aria-label").toLowerCase();
        WebElement brandInput = brandLi.findElement(By.tagName("input"));
        brandInput.sendKeys(Keys.SPACE);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        List<WebElement> searchResults = webDriver.findElements(By.xpath("//*[@data-component-type=\"s-search-result\"]"));
        for (WebElement el : searchResults) {
            WebElement h2 = el.findElement(By.tagName("h2"));
            assert h2.getText().toLowerCase().startsWith(brandName);
        }
    }
}
