package selenium001;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class FirstSeleniumTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void testGoogleSearch(){
        // Open the browser and navigate to the Google website
        driver.get("https://www.google.com");

        // Find the search input element and enter a search query
        driver.findElement(By.name("q")).sendKeys("yahoo");

        // Press Enter to perform the search
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // Wait for the search results to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("yahoo"));

        // Click on the first search result
        driver.findElement(By.className("LC20lb")).click();

        // Click on the "Sign Up" button
        driver.findElement(By.className("_yb_6k8xk")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Find the "Create Account" element and click on it if displayed, otherwise click on the "Sign Up" element
        WebElement signupAccountElement = driver.findElement(By.className("signups"));

       if (signupAccountElement.isDisplayed()){
            signupAccountElement.click();
        }else {
            System.out.println("Elements Not Found");
        }

        // Assert the results
        String expectedTitle = "Yahoo | Mail, Weather, Search, Politics, News, Finance, Sports & Videos";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterMethod
    public void tearDown(){
        // Close the browser
        driver.quit();
    }

}
