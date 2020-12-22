package stepDefinition;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import base.BaseUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;

public class InsertSingleRecord extends BaseUtil{
	private BaseUtil base;
	Scenario scenario;
	HttpPost request;
	HttpResponse httpResponse;
	HttpClient httpClient;
	public InsertSingleRecord(BaseUtil base) {
		this.base=base;
	}
	
	public InsertSingleRecord() {
	
	}
	@Given("^I set POST service API end point$")
		 public void I_set_PostEndpoint() throws ClassNotFoundException, FileNotFoundException{
		scenariodef.createNode(new GherkinKeyword("Given"),"I set POST service API end point");        
		FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String URI = p.getProperty("url_single_Insert");
		
		System.out.println("URL :"+URI);
		        
		        
		    }
    

	@When("^I Send a POST HTTP request with \"(.*?)\"\"(.*?)\"\"(.*?)\"\"(.*?)\"\"(.*?)\"\"(.*?)\"$")
	public void I_send_PostRequest(String birthday,String gender,String name,String natid,String salary, String tax) throws ClientProtocolException, IOException, ClassNotFoundException{
		scenariodef.createNode(new GherkinKeyword("When"),"I Send a POST HTTP request"); 
		System.out.println("Values = ");
	    System.out.println("{ \"birthday\":"+'"'+ birthday +'"'+",\"gender\":"+'"'+ gender+'"'+",\"name\":"+'"'+ name+'"'+",\"natid\":"+'"'+ natid+'"'+",\"salary\":"+ '"'+salary+'"'+",\"tax\": "+ '"'+tax+'"'+"}");
		this.httpClient = HttpClientBuilder.create().build();

	    this.request = new HttpPost("http://localhost:8080/calculator/insert");
	    StringEntity params = new StringEntity("{ \"birthday\":"+'"'+ birthday +'"'+",\"gender\":"+'"'+ gender+'"'+",\"name\":"+'"'+ name+'"'+",\"natid\":"+'"'+ natid+'"'+",\"salary\":"+ '"'+salary+'"'+",\"tax\": "+ '"'+tax+'"'+"}");
	    
	    this.request.addHeader("Content-Type", "application/json;charset=UTF-8");
	    this.request.addHeader("Accept","*/*");
	    this.request.setEntity(params);

	    System.out.println(this.request.getAllHeaders());
	    this.httpResponse = httpClient.execute(this.request);

	     System.out.println("response= "+this.httpResponse); 
	
    }
	
	@Then("\\({int})AC1:I receive a valid response code {int}")
	public void i_receive_valid_response(int a, int st) throws ClientProtocolException, IOException, ClassNotFoundException {
		 
		
		String jsonType = "application/json";
		System.out.println("Response = " + this.httpResponse.getStatusLine().getStatusCode());
		
		
		if(this.httpResponse.getStatusLine().getStatusCode()!=202) {
			scenariodef.createNode(new GherkinKeyword("Then"),"(1)AC1: I did not receive a valid response code 202"); 
			this.scenariodef.log(Status.FAIL, "Did not receive a valid response from HTTP POST API");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("Then"),"(1)AC1: I received a valid response code 202"); 
			this.scenariodef.log(Status.PASS, "Working Class Hero is Added successfully employeeId"); 
			System.out.println("Working Class Hero is Added successfully");    
		}
			   
	}
}
