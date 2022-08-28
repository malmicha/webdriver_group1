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
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class SearchField {
    WebDriver webDriver;


    @BeforeMethod
    public void openAmazonPage() {
        if (webDriver == null) {
            System.setProperty(
                    "webdriver.chrome.driver",
                    "C:\\Users\\Lesya\\IdeaProjects\\Test\\src\\test\\resources\\webdriver\\chromedriver.exe"
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
    public void search_non_result() {
        WebElement searchfield = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchfield.click();
        searchfield.sendKeys("/////////////777777777777777777777777777.");

        WebElement searchButton = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        WebElement isValue = webDriver.findElement(By.xpath("//*[text()='Try checking your spelling or use more general terms']"));
        assertTrue(isValue.getText().contains("Try checking your spelling or use more general terms"));
    }

    @Test
    public void result_field() {
        WebElement searchfield2 = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchfield2.click();
        searchfield2.sendKeys("laptop");

        WebElement searchButton2 = webDriver.findElement(By.id("nav-search-submit-button"));
        searchButton2.click();

        WebElement results = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/span/div/h1/div/div[1]/div/div/span[3]"));
        boolean isResult = results.getText().contains("laptop");
        Assert.assertTrue(isResult);

    }




    @Test
    public void search_field() {
        String underConsTitle = "lamptop";
        String baseUrl = "https://www.amazon.com/s?k=laptop&crid=2206F5TLCWA8D&sprefix=%2Caps%2C163&ref=nb_sb_noss";
        webDriver.get(baseUrl);
        List<WebElement> linkElements = webDriver.findElements(By.tagName("laptop"));
        String[] linkTexts = new String[linkElements.size()];
        int i = 0;
        for (WebElement e : linkElements) {
            linkTexts[i] = e.getText();
            i++;
        }

        for (String t : linkTexts) {
            webDriver.findElement(By.linkText(t)).click();
            if (webDriver.getTitle().equals(underConsTitle)) {

            } else {
                System.out.println("\"" + t + "\""
                        + " laptop.");
            }

        }

    }




}