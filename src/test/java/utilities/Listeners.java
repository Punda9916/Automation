package utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;

import java.io.IOException;

import org.testng.ITestContext;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
	ExtentReports extent =ExtentReportNG.getReport();
    @Override
    public void onTestStart(ITestResult result) {
    	
    	test =extent.createTest(result.getMethod().getMethodName());
    	
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	
    	test.log(Status.PASS, "Test Passed");
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	test.fail(result.getThrowable());
    	String file=null;
    	try {
			file=getScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Test Failed: " + result.getName());
        
		test.addScreenCaptureFromPath(file, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
    	extent.flush();
        System.out.println("Suite Finished: " + context.getName());
    }
}


