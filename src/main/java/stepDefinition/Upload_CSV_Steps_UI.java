package stepDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;


import base.BaseUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.UploadCSV_API;
import pages.UploadCSV_UI;
import util.CSVRowValues;

public class Upload_CSV_Steps_UI  extends BaseUtil {
	WebDriver driver;
	UploadCSV_UI uploadcsv_ui;
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
	public Upload_CSV_Steps_UI(BaseUtil base) {
		this.base=base;
	}
	
	public Upload_CSV_Steps_UI() {
	
	}

	
	@Given("^I open the application$")
	public void I_Open_the_url() throws ClassNotFoundException, IOException, InterruptedException {
		
		scenariodef.createNode(new GherkinKeyword("Given"),"I open the application ");
		uploadcsv_ui = new UploadCSV_UI(driver);
		uploadcsv_ui.openapplication();
		
	}
	
	@And("I Find a Browse Button")
	public void i_found_browse_button() throws ClassNotFoundException, InterruptedException {
		
		scenariodef.createNode(new GherkinKeyword("And"),"I Find a Browse Button");
		uploadcsv_ui = new UploadCSV_UI(driver);
		uploadcsv_ui.browsebutton();
	}
	@When("\\({int})AC3: There is an Upload CSV File Button")
	public void upload_csv_File_btn(int tc) throws ClassNotFoundException, InterruptedException, FileNotFoundException {
		
		
		uploadcsv_ui = new UploadCSV_UI(driver);
		if(uploadcsv_ui.upload_csv_file()) {
			scenariodef.createNode(new GherkinKeyword("When"),"(3)AC3: There is an Upload CSV File Button").pass("Pass");
			this.scenariodef.log(Status.PASS, "Upload csv file successful");
			
		}else
		{
			scenariodef.createNode(new GherkinKeyword("When"),"(3)AC3: There is an Upload CSV File Button").fail("Fail");
			this.scenariodef.log(Status.FAIL, "Upload csv file element not interactable");
		}
	}
	@Then("I upload the csv file")
	public void upload_csv_file_btn() throws ClassNotFoundException, InterruptedException, FileNotFoundException {
		scenariodef.createNode(new GherkinKeyword("Then"),"I upload the csv file");
		uploadcsv_ui = new UploadCSV_UI(driver);
		uploadcsv_ui.upload_csv_file();
	
	}
	@And("I can click Refresh Tax Relief Table button")
	public void click_tax_relief_btn() throws ClassNotFoundException, InterruptedException {
		
		scenariodef.createNode(new GherkinKeyword("And"),"I can click Refresh Tax Relief Table button");
		uploadcsv_ui = new UploadCSV_UI(driver);
		uploadcsv_ui.refresh_tax_lable();
	}
	@And("I am able to click Visit Swagger button")
	public void click_swagger_btn() throws ClassNotFoundException, InterruptedException {
		
		scenariodef.createNode(new GherkinKeyword("And"),"I am able to click Visit Swagger button");
		uploadcsv_ui = new UploadCSV_UI(driver);
		uploadcsv_ui.visit_swagger();
		uploadcsv_ui.close_driver();
	}
	
	
	
	
	
	
}
