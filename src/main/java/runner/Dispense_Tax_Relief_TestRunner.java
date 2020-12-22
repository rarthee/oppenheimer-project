package runner;


import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//Test runner for Tax Relief dispense test scenarios
@CucumberOptions(	
	features = {"C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\Feature\\Dispense_Tax_Relief.feature"},
		glue = "stepDefinition",
		//Add tags if we want to run specific scenarios 
		//tags ="@smoke,@regression",
		plugin={"pretty", "html:target/report" }
)
public class Dispense_Tax_Relief_TestRunner extends AbstractTestNGCucumberTests

{
	
	
	@Override
    @DataProvider
	 public Object[][] scenarios() {
	        return super.scenarios();
	    }

}