package org.example.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){

        String path=System.getProperty("user.dir")+"//reports//report.html";
        //Create ExtentSparkReporter object to configure details of our report
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("UI Automation");
        extentSparkReporter.config().setDocumentTitle("Test Automation Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        return extentReports;


    }
}
