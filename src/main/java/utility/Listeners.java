package utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseClass;



public class Listeners extends BaseClass implements ITestListener {

	public ExtentTest test;
	
	ExtentReports extent = Extent_Report.getReportObject();
	
	ThreadLocal<ExtentTest> paralleltest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		String methodname= result.getMethod().getMethodName();
		 test = extent.createTest(methodname);
		 paralleltest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		paralleltest.get().log(Status.PASS, "Test Successfully Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//it will throw test failure but it thread safe
		paralleltest.get().fail(result.getThrowable());
		
		WebDriver driver= null;
		String failMethodName= result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		try {
			paralleltest.get().addScreenCaptureFromPath(getScreenShotPath(failMethodName,driver),result.getMethod().getMethodName());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		paralleltest.get().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	

}
