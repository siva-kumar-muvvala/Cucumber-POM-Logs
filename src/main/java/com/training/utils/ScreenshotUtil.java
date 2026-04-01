package com.training.utils; 
 
import java.io.File; 
import java.io.IOException; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
 
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.TakesScreenshot; 
import org.openqa.selenium.WebDriver; 
 
public class ScreenshotUtil { 
 
    private ScreenshotUtil() { 
    } 
 
    public static byte[] captureScreenshotAsBytes(WebDriver driver) { 
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES); 
    } 
 
    public static String captureScreenshotToFile(WebDriver driver, String scenarioName) { 
        try { 
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); 
            String safeName = scenarioName.replaceAll("[^a-zA-Z0-9-_]", "_"); 
 
            Path screenshotDir = Paths.get("target", "screenshots"); 
            Files.createDirectories(screenshotDir); 
            
            String fileName = safeName + "_" + timeStamp + ".png"; 
            Path destination = screenshotDir.resolve(fileName); 
 
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
            Files.copy(sourceFile.toPath(), destination); 
 
            return destination.toString(); 
        } catch (IOException e) { 
            throw new RuntimeException("Unable to save screenshot file", e); 
        } 
    } 
} 