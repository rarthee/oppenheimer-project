package runner;
import org.testng.ITestContext ;		
import org.testng.ITestListener ;		
import org.testng.ITestResult ;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;

import base.ExtentReportUtil;	
import static base.BaseUtil.features;

import java.io.IOException;
public class NGTestListener implements ITestListener	{
	ExtentReportUtil extentReportUtil = new ExtentReportUtil();


	   public void onTestStart(ITestResult iTestResult) {					
		   			
	        		
	    }		

 	
    public void onTestFailure(ITestResult iTestResult) {					
    	
    	this.extentReportUtil.scenariodef.log(Status.FAIL, "Test FAILED");	
    	// Take screenshot on failure
    	
    		
			try {
				extentReportUtil.ExtentReportScreenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    	
        		
    }		

		
    public void onTestSkipped(ITestResult iTestResult) {					
    	 System.out.println("On test skipped");			
        		
    }	
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    	this.extentReportUtil.scenariodef.log(Status.INFO, "On Test Failed but within success percentage");
    	System.out.println("On test percentage");
    }

    public void onTestSuccess(ITestResult iTestResult) {		
    	System.out.println("onTestSuccess");
    	this.extentReportUtil.scenariodef.log(Status.PASS, "Test PASSED");		
        		
    }	
  //within onstart method, we will call the extent report method and from there we will create the features
  	public void onStart(ITestContext context) {					
       System.out.println("onStart");
      	extentReportUtil.ExtentReport();  //calling the extent report method in the extentreport util class
        //To call the feature
      	features = extentReportUtil.extent.createTest(Feature.class,"Oppenheimer Project");
        System.out.println("Feature.class " +features.toString());  
          
      }	
    public void onFinish(ITestContext context) {					
        System.out.println("On Finish");
        extentReportUtil.FlushReport();
       
    }	

}
