package selenium002;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
    }
    @BeforeMethod
    public void Navigate(){
        driver.get("https://google.com");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String currentTabHandle = driver.getWindowHandle();
        Set<String> allTabHandle = driver.getWindowHandles();

        for (String tabHandle: allTabHandle){
            if (!tabHandle.equals(currentTabHandle)){
                driver.switchTo().window(tabHandle);
                break;
            }
        }driver.switchTo().window(currentTabHandle);
    }
    @Test
    public void testLogin(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage.login("Admin","admin123");
    }
    @AfterClass
    public void tearDown(){
       // driver.quit();
    }


}
