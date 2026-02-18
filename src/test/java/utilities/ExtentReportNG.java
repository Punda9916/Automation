package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReport() {
		
		//ExtentSparkReporter - aventstack 
		String FilePath = System.getProperty("user.dir" )+ "\\src\\test\\resources\\reports\\index.html";
		ExtentSparkReporter report= new ExtentSparkReporter(FilePath);
		report.config().setReportName("Web Automation");
		report.config().setDocumentTitle("Automation Results");
		
		
		ExtentReports extent = new ExtentReports();
	    extent.attachReporter(report);
	    extent.createTest(FilePath);
	    extent.setSystemInfo("Tester", "Nagaveni");
	    return extent;
				}

}
