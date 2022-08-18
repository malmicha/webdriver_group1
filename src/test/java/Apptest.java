import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Apptest {
    @Test
    public void openMainPageTest() {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\mich\\IdeaProjects\\webdriver_group1\\src\\test\\resources\\webdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.amazon.com/");

        driver.close();
        driver.quit();
    }
}
