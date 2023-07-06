package ExtentReports_TestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class SquirrelPartyTest {
    private static ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeSuite
    public void setUpExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/extent-report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void tearDownExtentReports() {
        extentReports.flush();
    }

    @BeforeMethod
    public void setUpExtentTest(ITestResult testResult) {
        String testName = testResult.getMethod().getMethodName();
        extentTest = extentReports.createTest(testName);
    }

    @AfterMethod
    public void tearDownExtentTest(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "Test failed: " + testResult.getThrowable());
        } else if (testResult.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, "Test skipped: " + testResult.getThrowable());
        } else {
            extentTest.log(Status.PASS, "Test passed");
        }
    }

    @Test
    public void testSuccessfulPartyOnWeekday() {
        boolean isWeekend = false;
        int cigars = 50;
        boolean expected = true;
        boolean actual = SquirrelParty.isPartySuccessful(cigars, isWeekend);
        Assert.assertEquals(actual, expected, "Party is not successful on a weekday with 50 cigars");
    }

    @Test
    public void testSuccessfulPartyOnWeekend() {
        boolean isWeekend = true;
        int cigars = 40;
        boolean expected = true;
        boolean actual = SquirrelParty.isPartySuccessful(cigars, isWeekend);
        Assert.assertEquals(actual, expected, "Party is not successful on a weekend with 40 cigars");
    }

    @Test
    public void testUnsuccessfulPartyOnWeekday() {
        boolean isWeekend = false;
        int cigars = 30;
        boolean expected = false;
        boolean actual = SquirrelParty.isPartySuccessful(cigars, isWeekend);
        Assert.assertEquals(actual, expected, "Party is successful on a weekday with 30 cigars");
    }

    @Test
    public void testUnsuccessfulPartyOnWeekend() {
        boolean isWeekend = true;
        int cigars = 70;
        boolean expected = false;
        boolean actual = SquirrelParty.isPartySuccessful(cigars, isWeekend);
        Assert.assertEquals(actual, expected, "Party is successful on a weekend with 70 cigars");
    }
}
