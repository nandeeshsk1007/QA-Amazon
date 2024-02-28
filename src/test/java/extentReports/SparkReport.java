package extentReports;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SparkReport implements ITestListener {
	ChromeDriver driver;
	@BeforeTest
	public void setup()
	{
		 driver = new ChromeDriver();
		
		driver.get("http://www.facebook.com");
	}
	
	@Test
	public void action()
	{
		ExtentSparkReporter extentspark = new ExtentSparkReporter("extent.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(extentspark);
		extentspark.config().setDocumentTitle("sample title");
		extentspark.config().setReportName("sample report name");
		extentspark.config().setTheme(Theme.DARK);
		ExtentTest test = extent.createTest("sample test");
		test.log(Status.PASS, "test passed");
//		test.log(Status.INFO, "test information");
//		test.log(Status.FAIL, "test failed");
		test.log(Status.SKIP, "test skipped");
	//	test.log(Status.WARNING, "test warning");
		extent.setSystemInfo("OS", "Windows 11");
		extent.flush();
	}
	
	@BeforeTest
	public void teardown()
	{
		driver.quit();
	}

}
