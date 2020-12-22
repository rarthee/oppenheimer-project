package runner;


import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//Test runner for Book Keeper related test scenarios
@CucumberOptions(	
	features = {"C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\Feature\\BookKeeper_Query_Tax_Relf.feature"},
		glue = "stepDefinition",
		//Add tags if we want to run specific scenarios
		//tags ="@QueryTaxRelief"
		plugin={"pretty", "html:target/report" }
)
public class BookKeeperTax_TestRunner extends AbstractTestNGCucumberTests

{
	
	
	@Override
    @DataProvider
	 public Object[][] scenarios() {
	        return super.scenarios();
	    }

}