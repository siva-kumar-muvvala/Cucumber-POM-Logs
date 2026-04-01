package com.training.factory; 
 
import java.time.Duration; 
 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.chrome.ChromeOptions; 
 
import com.training.utils.ConfigReader; 
 
public class DriverFactory { 
 
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>(); 
 
    private DriverFactory() { 
    } 
 
    public static void initDriver() { 
        String browser = ConfigReader.get("browser"); 
        boolean headless = ConfigReader.getBoolean("headless"); 
 
        if (browser == null || browser.equalsIgnoreCase("chrome")) { 
            ChromeOptions options = new ChromeOptions(); 
            options.addArguments("--start-maximized"); 
 
            if (headless) { 
                options.addArguments("--headless=new"); 
                options.addArguments("--window-size=1920,1080"); 
            } 
 
            driver.set(new ChromeDriver(options)); 
        } else { 
            throw new IllegalArgumentException("Unsupported browser: " + browser); 
        } 
 
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait"))); 
    } 
 
    public static WebDriver getDriver() { 
        return driver.get(); 
    } 
 
    public static void quitDriver() { 
        WebDriver webDriver = driver.get(); 
        if (webDriver != null) { 
            webDriver.quit(); 
            driver.remove(); 
        } 
    } 
} 