package com.training.reports;
 
import com.aventstack.extentreports.ExtentTest;
 
public class ExtentTestHolder {
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
 
    public static void set(ExtentTest extentTest) { test.set(extentTest); }
    public static ExtentTest get() { return test.get(); }
    public static void remove() { test.remove(); }
}