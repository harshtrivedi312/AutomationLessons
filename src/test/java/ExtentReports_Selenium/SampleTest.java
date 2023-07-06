package ExtentReports_Selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;


public class SampleTest {
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeSuite
    public void setUpExtentReports() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/Report/report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void setUpBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Method to take a screenshot and save it to a specified path
    public static String takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);

        BufferedImage screenshotImage = ImageIO.read(SrcFile);
        ImageIO.write(screenshotImage,"png",DestFile);

        FileUtils.copyFile(SrcFile, DestFile);
        System.out.println(SrcFile.length());
        return DestFile.getPath();
    }

    @Test
    public void googleSearch() {
        extentTest = extentReports.createTest("googleSearchTest");
        driver.get("https://google.com");
    }

    @AfterMethod
    public void saveScreenshot(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "Test failed: " + result.getThrowable());
            //take a screenShot if test Fail

            String screenshotPath = takeSnapShot(driver, "test-output/screenshots/FailScreenshot.png");
            File screenshotFile = new File(screenshotPath);
            if (screenshotFile.exists()){
            extentTest.addScreenCaptureFromPath(screenshotFile.getPath());
            }else {
                System.out.println("screenshot not found");
                extentTest.log(Status.WARNING,"Screenshot file not found");
            }
        }
        else {
            extentTest.log(Status.PASS, "We are on the Google search page");
            String screenshotPath = takeSnapShot(driver, "test-output/screenshots/PassScreenshot.png");
            File screenshotFile = new File(screenshotPath);
            if (screenshotFile.exists()){
                extentTest.addScreenCaptureFromPath(screenshotFile.getPath());
            }else {
                System.out.println("screenshot not found");
                extentTest.log(Status.WARNING,"Screenshot file not found");
            }
        }

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void tearDownExtentReports() {
        extentReports.flush();
    }
}
