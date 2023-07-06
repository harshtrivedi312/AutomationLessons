package selenium001;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementRepository {
    private WebDriver driver;

    public ElementRepository(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFirstNameField() {
        return driver.findElement(By.id("RESULT_TextField-1"));
    }

    public WebElement getLastNameField() {
        return driver.findElement(By.id("RESULT_TextField-2"));
    }

    public WebElement getPhoneField(){
        return driver.findElement(By.id("RESULT_TextField-3"));
    }

    public WebElement getCountryField(){
        return driver.findElement(By.id("RESULT_TextField-4"));
    }
    public WebElement getCityField(){
        return driver.findElement(By.id("RESULT_TextField-5"));
    }

    public WebElement getEmailField(){
        return driver.findElement(By.id("RESULT_TextField-6"));
    }

    public WebElement getGender() {
        return driver.findElement(By.xpath("//*[@id=\"RESULT_RadioButton-7_0\"]"));
    }
    public WebElement submitButton(){
      return  driver.findElement(By.xpath("//input[@type='submit']"));
    }

    public WebElement FileUpload(){
        return driver.findElement(By.id("RESULT_FileUpload-10"));
    }
}
