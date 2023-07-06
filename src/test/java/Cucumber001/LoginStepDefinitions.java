package Cucumber001;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class LoginStepDefinitions {
    private WebDriver driver;


    @Given("^I am on the login page$")
    public void i_am_on_the_login_page() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @When("^I enter valid credentials$")
    public void i_enter_valid_credentials() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.className("orangehrm-login-button")).click();
    }
    @Then("^I should be logged in$")
    public void i_should_be_logged_in() {
        String ExpectedTitle = "OrangeHRM";
        String Actual= driver.getTitle();
        Assert.assertEquals(ExpectedTitle,Actual,"Login Success");
        driver.quit();
    }

    @When("I enter invalid credentials")
    public void i_enter_invalid_credentials(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("Admin1234");
        driver.findElement(By.className("orangehrm-login-button")).click();
    }
    @Then("I should not be able to login")
    public void i_Should_not_be_able_to_login(){
        String Expected = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String Actual= "Invalid credentials";
        Assert.assertEquals(Expected,Actual);
        driver.quit();
    }

}
