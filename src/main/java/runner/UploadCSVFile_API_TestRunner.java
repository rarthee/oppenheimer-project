package runner;


import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//Test runner for testing the API to upload a CSV file
@CucumberOptions(	
	features = {"C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\Feature\\UploadCSVFile_API.feature"},
		glue = "stepDefinition",
		//Add tags if we want to run specific scenarios
		//tags ="@smoke,@regression",
		plugin={"pretty", "html:target/report" }
)
public class UploadCSVFile_API_TestRunner extends AbstractTestNGCucumberTests

{
	
	
	@Override
    @DataProvider
	 public Object[][] scenarios() {
	        return super.scenarios();
	    }

}