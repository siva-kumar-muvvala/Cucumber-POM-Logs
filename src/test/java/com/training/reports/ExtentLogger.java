package com.training.reports;
 
import com.aventstack.extentreports.ExtentTest;
 
public class ExtentLogger {
    public static void step(String msg) {
        ExtentTest t = ExtentTestHolder.get();
        if (t != null) t.info("STEP: " + msg);
    }
}