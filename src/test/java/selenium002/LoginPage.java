package selenium002;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private WebDriver driver;

    private By userNameField = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input");
    private By passwordField = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input");
    private By loginButton = By.xpath("//button[@type='submit' and contains(@class, 'oxd-button') and contains(@class, 'orangehrm-login-button')]");

    public LoginPage(WebDriver driver){
        this.driver= driver;
    }

    public void setUsername(String username){
        driver.findElement(userNameField).click();
        driver.findElement(userNameField).sendKeys(username);
    }
    public void setPassword(String password){
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void login(String username,String password){
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }


}
