package com.training.stepdefinitions;
 
import com.training.factory.DriverFactory;
import com.training.reports.ExtentLogger;
import com.training.utils.ExcelReader;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.testng.Assert;
 
import java.util.Map;
 
public class LoginSteps {
 
    private WebDriver driver;
    private Map<String, String> rowData;
 
 
@Given("user launches the application")
public void user_launches_the_application() {
    driver = DriverFactory.getDriver();
    ExtentLogger.step("Open URL: https://the-internet.herokuapp.com/login");
    driver.get("https://the-internet.herokuapp.com/login");
}
 
 
    @When("user logs in using excel row {int}")
    public void user_logs_in_using_excel_row(Integer rowNumber) {
 
        rowData = ExcelReader.getRowData(
                "testdata/LoginData.xlsx",
                "LoginData",
                rowNumber
        );
        
        String username = rowData.get("username");
        ExtentLogger.step("Enter username: " + username);
        String password = rowData.get("password");
        ExtentLogger.step("Enter password: " + password);
 
        if (username == null || password == null) {
            throw new RuntimeException("Excel data invalid. Username or password is null.");
        }
 
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
 
        // ✅ Correct locator for HerokuApp
        ExtentLogger.step("Click Login button");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
 
    @Then("user should see expected result from excel")
    public void user_should_see_expected_result_from_excel() {
 
        String expectedMessage = rowData.get("expectedMessage");
 
        // ✅ HerokuApp always shows message in #flash
        WebElement flashMessage = driver.findElement(By.id("flash"));
        String actualText = flashMessage.getText();
 
        Assert.assertTrue(
                actualText.contains(expectedMessage),
                "Expected message not displayed. Expected: " + expectedMessage +
                        " Actual: " + actualText
        );
    }
}
 