package com.trendyol.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports()
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./temp/trendyol-report.html");
        reporter.config().setReportName("Trendyol Test Report");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
