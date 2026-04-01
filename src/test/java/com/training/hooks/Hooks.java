package com.training.hooks;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.training.factory.DriverFactory;
import com.training.reports.ExtentManager;
import com.training.reports.ExtentTestHolder;
import com.training.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private static final ExtentReports extent = ExtentManager.getExtent();

    @Before
    public void setUp(Scenario scenario) {

        // Initialize WebDriver
        DriverFactory.initDriver();
        logger.info("Browser launched for scenario: {}", scenario.getName());

        // Create Extent test
        ExtentTest test = extent.createTest(scenario.getName());
        scenario.getSourceTagNames().forEach(test::assignCategory);

        ExtentTestHolder.set(test);
        test.info("Browser successfully launched");
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        try {
            if (driver != null && scenario.isFailed()) {
                logger.error("Scenario failed: {}", scenario.getName());

                ExtentTestHolder.get().fail("Scenario failed");

                byte[] screenshotBytes =
                        ScreenshotUtil.captureScreenshotAsBytes(driver);
                scenario.attach(
                        screenshotBytes,
                        "image/png",
                        scenario.getName()
                );

                String screenshotPath =
                        ScreenshotUtil.captureScreenshotToFile(driver, scenario.getName());

                ExtentTestHolder.get()
                        .addScreenCaptureFromPath(screenshotPath);

                logger.info("Screenshot saved at: {}", screenshotPath);
            } else {
                ExtentTestHolder.get().pass("Scenario passed");
            }

        } catch (Exception e) {
            logger.error("Error during teardown: ", e);
        } finally {
            logger.info("Closing browser");
            DriverFactory.quitDriver();

            extent.flush();
            ExtentTestHolder.remove();
        }
    }
}