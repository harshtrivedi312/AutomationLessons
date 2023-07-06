package SlackMessageTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;



public class SlackMessage {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {

       WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);  // Disable desktop notifications

        driver = new FirefoxDriver(options);
    }

    @Test
    public void loginToSlack() {

        driver.get("https://slack.com/signin#/signin");


        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        wait.until(ExpectedConditions.titleContains("Login | Slack"));

        String title = driver.getTitle();
        String Expected = "Login | Slack";
        Assert.assertEquals(title,Expected);


        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/div[4]/span"),"sign in manually instead."));

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[4]/span/a")).click();
        driver.findElement(By.xpath("//*[@id=\"domain\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"domain\"]")).sendKeys("creativecodingcollect");
        driver.findElement(By.xpath("/html/body/main/div/div/div/div[1]/div[2]/form/button")).click();
        driver.findElement(By.xpath("//*[@id=\"page_contents\"]/div/div/div[2]/div[3]/div[4]/span/a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.findElement(By.xpath("//*[@id=\"email\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("htrivedi.developer@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("M@inboard1");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"signin_btn\"]")).click();

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/p/a")).click();


        driver.findElement(By.xpath("//*[@id=\"C058KJYKP24\"]/div/span[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[5]/div[2]/div/div[4]/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/div[1]")).click();

        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[5]/div[2]/div/div[4]/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/div/div[1]"));

            element.click();
            element.sendKeys("DO YOUR HOMEWORK");
            element.sendKeys(Keys.ENTER);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
