package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportUtil extends BaseUtil{

	//Create the filename
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
	String fileName = reportlocation + "extentReport"+ timeStamp + ".html";
	
	public void ExtentReport() {
		
		
	   
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Oppenheimer Project Test Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Oppenheimer Project Test Report");
		htmlReporter.start();
		
		//To create extentreports objects
				extent = new ExtentReports();
		//attach the html reporter to extent report
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname","LocalHost");
		extent.setSystemInfo("OS","Windows8");
		extent.setSystemInfo("Tester Name","Arthee R");
	
			
	}
	//To add screenshot if a test case fails
	public void ExtentReportScreenshot() throws IOException {
	 File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 FileUtils.copyFile(src, new File(reportlocation+"screenshot.png"));
	 scenariodef.fail("details").addScreenCaptureFromPath(reportlocation+"screenshot.png");
	 
	}
	//to flush the report
	public void FlushReport() {
		
		extent.flush();
		
	}
}
