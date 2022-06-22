package com.trendyol.extensions;

import com.trendyol.report.ExtentReportManager;
import com.trendyol.report.ExtentTestManager;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class ReportExtension implements TestWatcher, BeforeTestExecutionCallback, AfterTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext)
    {

        String testName = extensionContext.getTestClass().get().getSimpleName();
        String testDescription = "Class: " + extensionContext.getTestClass().get().getSimpleName() + " Method: " + extensionContext.getTestMethod().get().getName();

        ExtentTestManager.startTest(testName, testDescription);
    }



    @Override
    public void testSuccessful(ExtensionContext context)
    {
        log.info("Test Successful {}", context.getTestClass().get().getSimpleName());
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable cause)
    {
        log.info("Test Failed {}", extensionContext.getTestClass().get().getSimpleName());

    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        ExtentReportManager.extentReports.flush();
    }
}
