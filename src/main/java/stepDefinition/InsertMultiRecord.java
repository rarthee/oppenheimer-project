package stepDefinition;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpStatus;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;


import base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.ReadFile;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;

public class InsertMultiRecord extends BaseUtil{
	private BaseUtil base;
	Scenario scenario;
	HttpPost request;
	HttpResponse httpResponse;
	HttpClient httpClient;
	ReadFile readxlsobj;
	String URI;
	String multi_insert_file;
	public InsertMultiRecord(BaseUtil base) {
		this.base=base;
	}
	
	public InsertMultiRecord() {
	
	}
	@Given("^I set POST service API end point for multiperson$")
		 public void I_set_PostEndpoint() throws ClassNotFoundException, FileNotFoundException{
				scenariodef.createNode(new GherkinKeyword("Given"),"I set POST service API end point for multiperson");     
				FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
				Properties p = new Properties();
				try {
					p.load(reader);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.URI= p.getProperty("multi_person_insert_UI");
				multi_insert_file=p.getProperty("multi_insert_file");
		        System.out.println("URL :"+URI);
		    }
    
	
	@When("^I Send a POST HTTP request for multiperson$")
	public void I_send_PostRequest() throws ClientProtocolException, IOException, ClassNotFoundException{
		
		List<String> details;
		
		scenariodef.createNode(new GherkinKeyword("When"),"I Send a POST HTTP request for multiperson");
		this.httpClient = HttpClientBuilder.create().build();
			
		
		this.request = new HttpPost(this.URI);
	   
	    List<String> body;
	    body=new ArrayList<String>();
	    readxlsobj = new ReadFile();
	    body= readxlsobj.readfile(multi_insert_file);
	    
	    String entity = body.toString();
	    //entity='"'+entity+'"';
	    StringEntity params = new StringEntity(entity);
	    System.out.println(entity);
	   // StringEntity params = new StringEntity("[{ \"birthday\":"+'"'+ birthday +'"'+",\"gender\":"+'"'+ gender+'"'+",\"name\":"+'"'+ name+'"'+",\"natid\":"+'"'+ natid+'"'+",\"salary\":"+ '"'+salary+'"'+",\"tax\": "+ '"'+tax+'"'+"}]");
	    request.addHeader("Content-Type", "application/json;charset=UTF-8");
	    request.addHeader("Accept","*/*");
	    request.setEntity(params);
	    System.out.println(request.getAllHeaders());
	   // HttpResponse response = httpClient.execute(request);
	    
	   // System.out.println(response.toString());

	      
	
    }
	
	@Then("\\({int})AC1:I receive a valid response code {int} for multiperson")
	public void i_receive_valid_response(int tc, int st) throws ClientProtocolException, IOException, ClassNotFoundException {
		

		this.httpResponse = this.httpClient.execute(request);
		String jsonType = "application/json";
		System.out.println("Response = " + this.httpResponse.getStatusLine().getStatusCode());
		
		
		if(this.httpResponse.getStatusLine().getStatusCode()!=202) {
			scenariodef.createNode(new GherkinKeyword("Then"),"(2)AC1: I received a valid response code 202 for multiperson").fail("Fail");
			this.scenariodef.log(Status.FAIL, "Did not receive a valid response from HTTP POST API"+ this.httpResponse.getStatusLine().getStatusCode());
		}
		else {
			scenariodef.createNode(new GherkinKeyword("Then"),"(2)AC1: I did not receive a valid response code 202 for multiperson").pass("Pass");
			this.scenariodef.log(Status.PASS, "Successfully inserted multiple records through HTTP POST API");
			 System.out.println("Working Class Heros are Added successfully");    
		}
			   
	}
}
