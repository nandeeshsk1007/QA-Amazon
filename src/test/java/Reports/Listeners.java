package Reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {

	ExtentReports extentreport;
	ExtentTest extentTest;
	
	public void onStart(ITestContext context) {
		extentreport = ExtentReport.extentReport();
	}

	public void onTestStart(ITestResult result) {
		extentTest = extentreport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"execution started");
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"got failed");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+"successfully executed");
		
	}

	public void onTestFailure(ITestResult result) {
		try {
			result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstanceName());
		} 
		catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
	}


}
