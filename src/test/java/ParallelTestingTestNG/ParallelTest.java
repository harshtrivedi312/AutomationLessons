package ParallelTestingTestNG;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import selenium001.ElementRepository;

import java.time.Duration;
import java.util.List;

public class ParallelTest {
    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
               driver.manage().window().maximize();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            case "safari" ->{
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                driver.manage().window().maximize();
            }

            default -> throw new IllegalArgumentException("Invalid browser specified: " + browser);
        }
    }

    @Test
    public void FillMyForm() {

        driver.get("https://fs2.formsite.com/meherpavan/form2/index.html?1537702596407");
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Selenium Practice Form", pageTitle);

        ElementRepository repo = new ElementRepository(driver);

        repo.getFirstNameField().sendKeys("John");
        repo.getLastNameField().sendKeys("Doe");
        repo.getPhoneField().sendKeys("777-231-5677");
        repo.getCountryField().sendKeys("USA");
        repo.getCityField().sendKeys("McLean");
        repo.getEmailField().sendKeys("JohnDoe@gmail.com");
        WebElement genderRadioButton = repo.getGender();


       Actions actions = new Actions(driver);
       actions.moveToElement(genderRadioButton).click().perform();

       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"q15\"]/table/tbody")));

        WebElement checkboxesContainer = driver.findElement(By.xpath("//*[@id=\"q15\"]/table/tbody/tr"));
        List<WebElement> checkboxes = checkboxesContainer.findElements(By.tagName("input"));

        for (WebElement checkbox : checkboxes) {
            String labelText = checkbox.getAttribute("value");
            if (!labelText.equalsIgnoreCase("CheckBox-0") && !labelText.equalsIgnoreCase("CheckBox-6")) {
                checkbox.click();
            }
        }


        WebElement dropdown = driver.findElement(By.id("RESULT_RadioButton-9"));
        Select select = new Select(dropdown);
        select.selectByValue("Radio-1");

        repo.FileUpload().sendKeys("/Users/harsh/desktop/example.txt");

        repo.submitButton().click();

        String title = driver.getTitle();
        String expectedTitle = null;

        String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase();
        switch (browserName) {
            case "chrome":
            case "safari":
                expectedTitle = "Selenium Practice Form";
                break;
            case "firefox":
                expectedTitle = "Error";
                break;
            default:
                throw new UnsupportedOperationException("Unsupported Browser: " + browserName);
        }

        Assert.assertEquals(title, expectedTitle);

    }


 @AfterMethod
   public void tearDown() {
     if (driver != null) {
          driver.quit();
       }
  }

}