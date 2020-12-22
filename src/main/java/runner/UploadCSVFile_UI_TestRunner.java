package runner;


import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//Test runner for testing of uploading a CSV file through UI
@CucumberOptions(	
	features = {"C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\Feature\\UploadCSVFile_UI.feature"},
		glue = "stepDefinition",
		//Add tags if we want to run specific scenarios
		//tags ="@smoke,@regression",
		plugin={"pretty", "html:target/report" }
)
public class UploadCSVFile_UI_TestRunner extends AbstractTestNGCucumberTests

{
	
	
	@Override
    @DataProvider
	 public Object[][] scenarios() {
	        return super.scenarios();
	    }

}