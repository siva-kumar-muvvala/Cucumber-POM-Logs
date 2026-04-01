package com.training.reports;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class ExtentManager {
 
    private static ExtentReports extent;
 
    public static ExtentReports getExtent() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/extent-report/ExtentReport.html";
 
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Cucumber Advanced HTML Report");
            spark.config().setReportName("The Internet App Demo - Application");
 
            extent = new ExtentReports();
            extent.attachReporter(spark);
 
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }
        return extent;
    }
}