package org.example.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.example.resources.ExtentReporterNG;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extentReports=ExtentReporterNG.getReportObject();

    //to resolve thread concurrency issue. Since test object of ExtentTest class was
    //getting overridden when running test parallely.We are using ThreadLocal for thread safe execution
    ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        //to create entry for any test we need to add below line of codes
        test=extentReports.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS,"Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        extentTestThreadLocal.get().fail(result.getThrowable());

        //to give life to driver
        try {
            driver= (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {

            e.printStackTrace();
        }

        //Take screenshot
        String filePath;
        try {
             filePath=getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTestThreadLocal.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());

    }


    public void onTestSkipped(ITestResult result) {
        extentTestThreadLocal.get().log(Status.SKIP,"Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
