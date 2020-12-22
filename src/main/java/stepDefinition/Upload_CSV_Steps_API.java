package stepDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import base.BaseUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.UploadCSV_API;
import util.CSVRowValues;


public class Upload_CSV_Steps_API  extends BaseUtil {
	WebDriver driver;
	UploadCSV_API uploadcsv_api;
	private BaseUtil base;
	Scenario scenario;
	HttpPost request;
	HttpResponse httpResponse;
	HttpClient httpClient;
	CSVRowValues Csvrowvalues;
	Map<String, Integer> headerdetails ;
	List<String> fieldsArrayList ;
	int rownum;
	public Upload_CSV_Steps_API(BaseUtil base) {
		this.base=base;
	}
	
	public Upload_CSV_Steps_API() {
	
	}

	@Given("\\({int})AC1: First Row of a CSV file is of format {string}")
	public void First_row_format(int tc,String headerformat) throws ClassNotFoundException, IOException {
		String[] splitstr = headerformat.split(" ");
		
		//Read the readrow class to get the first row fields and validate them
		Csvrowvalues = new CSVRowValues();
		
		headerdetails = Csvrowvalues.readheader();
		
		for(int i=0;i<headerdetails.size();i++) {
			
			if(headerdetails.containsKey(splitstr[i])) {
				scenariodef.createNode(new GherkinKeyword("Given"),"(3)AC1: First Row of a CSV file has "+splitstr[i]+"").pass("Pass");
				this.scenariodef.log(Status.PASS, "(3)AC1: First row name of the csv file is expected. The value is "+splitstr[i]);
							
			}
			else {
				
				scenariodef.createNode(new GherkinKeyword("Given"),"(3)AC1: First Row of a CSV file is not of format natid, name, gender,salary, birthday, tax").fail("Fail");
				this.scenariodef.log(Status.FAIL, "(3)AC1: First row name of the csv file is not as expected. Incorrect value is "+splitstr[i]);
			}
			
						
			
		}
		
	}
	
	
	@When("\\({int})AC2: I read rows of data for each of the fields")
	public void i_read_rows_of_data(int tc) throws InterruptedException, ClassNotFoundException, IOException{
    
		scenariodef.createNode(new GherkinKeyword("When"),"(3)AC2: I read rows of data for each of the fields");
		Csvrowvalues = new CSVRowValues();
		for(int i=0;i<100;i++) {
			fieldsArrayList = Csvrowvalues.readrows(i);
		}
	
    }
	@When("^I open the url$")
	public void openurl() throws ClassNotFoundException, InterruptedException, FileNotFoundException {
		scenariodef.createNode(new GherkinKeyword("When"),"I open url");
		uploadcsv_api = new UploadCSV_API(driver);
		uploadcsv_api.openurl();
	}
	@And("^I click Try Out button$")
	public void click_try_out_btn() throws ClassNotFoundException, InterruptedException {
		scenariodef.createNode(new GherkinKeyword("And"),"I click Try Out button");
		uploadcsv_api = new UploadCSV_API(driver);
		uploadcsv_api.clicktryoutbtn();
	}
	@Then("\\({int})AC3: Check if upload button exists to upload")
	public void if_upload_button_exists(int tc) throws ClassNotFoundException {
		
		uploadcsv_api = new UploadCSV_API(driver);
		
		if(uploadcsv_api.check_upload_btn()) {
			scenariodef.createNode(new GherkinKeyword("Then"),"(3)AC3: Check if upload button exists to upload").pass("Pass");
			this.scenariodef.log(Status.PASS, "Choose File Button upload csv file is enabled");
		}
		else
		{
			scenariodef.createNode(new GherkinKeyword("Then"),"(3)AC3: Check if upload button exists to upload").fail("Fail");
			this.scenariodef.log(Status.FAIL, "Choose File Button upload csv file is not enabled");
		}
		
	}
	
	@And("^I am Able to click a button  to upload the file from the pc to portal$")
	public void able_to_click_upload_button() throws ClassNotFoundException, InterruptedException, FileNotFoundException {
		scenariodef.createNode(new GherkinKeyword("And"),"Able to click a button  to upload the file from the pc to portal");
		
		uploadcsv_api = new UploadCSV_API(driver);
				
		uploadcsv_api.uploadthefile();
	}
	
	@And("^I Click Execute$")
	public void click_execute() throws ClassNotFoundException {
		
		scenariodef.createNode(new GherkinKeyword("And"),"I Click Execute");
		
		uploadcsv_api = new UploadCSV_API(driver);
				
		uploadcsv_api.click_execute();
	}
	
	@And("^Validate if uploaded successfully with response code 200$")
	public void validate_result() throws ClassNotFoundException, InterruptedException {
		scenariodef.createNode(new GherkinKeyword("And"),"Validate if uploaded successfully with response code 200");
		
		uploadcsv_api = new UploadCSV_API(driver);
				
		String result =uploadcsv_api.validate_result();
		if(result.contains("200")) {
			
			this.scenariodef.log(Status.PASS, "csv file is uploaded successfully");
		}
		else
		{
			this.scenariodef.log(Status.FAIL, "File is not uploaded successfully. Failed with message "+result);
		}
	    uploadcsv_api.close_driver();
	
	}
	
	
}
