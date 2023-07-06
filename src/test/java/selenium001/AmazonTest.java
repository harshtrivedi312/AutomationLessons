package selenium001;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmazonTest {
    private WebDriver driver;

    @BeforeMethod
    public void SetUp() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.cache.disk.enable", false);
        profile.setPreference("browser.cache.memory.enable", false);
        profile.setPreference("browser.cache.offline.enable", false);
        profile.setPreference("network.http.use-cache", false);
        options.setProfile(profile);

        driver = new FirefoxDriver(options);
    }

    @Test(priority = 1)
    public void AmazonSearch() {
        driver.get("https://www.google.com");
        driver.navigate().to("https://www.amazon.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        String url = driver.getCurrentUrl();
        String expectedURL = "https://www.amazon.com/";
        Assert.assertEquals(url, expectedURL);

        String title = driver.getTitle();
        String amazonTitle = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(amazonTitle, title);

        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        searchBar.click();
        searchBar.sendKeys("socks");
        searchBar.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increase the timeout duration to 20 seconds
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/div[1]/div[2]/span/div/h1/div/div[1]/div/div/span[3]"), "socks"));

        String actual = driver.findElement(By.xpath("/html/body/div[1]/div[2]/span/div/h1/div/div[1]/div/div/span[3]")).getText();
        String expected = "\"socks\"";
        Assert.assertEquals(actual, expected);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> sockElements = driver.findElements(By.cssSelector(".s-result-item"));
        int total = countSocks(sockElements);
        System.out.println("Total socks count: " + total);
    }

    private static int countSocks(List<WebElement> sockElements) {
        int count = 0;
        for (WebElement item : sockElements) {
            if (item.getText().contains("sock")) {
                count++;
            }
        }
        return count;
    }

    @AfterMethod
    public void Clean() {
        driver.quit();
    }
}
