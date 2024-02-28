package Reports;

import java.io.File;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	@Test
	public static ExtentReports extentReport()
	{
		ExtentReports extentreport = new ExtentReports();
		
		File file = new File("./test-output/Extentreports/extentreport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(file);
		sparkreporter.config().setDocumentTitle("Amazon ecom app testing report");
		sparkreporter.config().setReportName("Test report");
		sparkreporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		sparkreporter.config().setTheme(Theme.STANDARD);
		sparkreporter.config().setTheme(Theme.DARK);
		
		extentreport.attachReporter(sparkreporter);
		return extentreport;
	}
}
