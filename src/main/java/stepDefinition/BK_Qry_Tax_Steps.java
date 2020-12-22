package stepDefinition;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;


import base.BaseUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import util.DataComparison;
import util.ReadFile;
import util.TaxCalculator;

public class BK_Qry_Tax_Steps  extends BaseUtil {

	
	private BaseUtil base;
	Scenario scenario;
	HttpPost request;
	HttpResponse httpResponse;
	HttpClient httpClient;
	HttpEntity entity ;
	TaxCalculator taxcalculator;
	ReadFile readfile;
	List<String> my_taxRelief;
	String result_str;
	boolean ans=true;
	int rownum;
	String URI;
	List<String> input;
	List<String> difference;
	DataComparison datacomparison;
	
	public BK_Qry_Tax_Steps(BaseUtil base) {
		this.base=base;
	}
	
	public BK_Qry_Tax_Steps() {
	
	}

	
	@Given("^I set up the url  for GET endpoint$")
	public void set_up_url() throws ClassNotFoundException, IOException, InterruptedException {
		
		
		URI = "http://localhost:8080/calculator/taxRelief";
		scenariodef.createNode(new GherkinKeyword("Given"),"I set up the url  for GET endpoint").pass("Pass");
		
	}
	
	
	@When("I Send a GET HTTP request")
	public void send_http_req() throws ClassNotFoundException, InterruptedException, ClientProtocolException, IOException {
	    HttpUriRequest request = new HttpGet(this.URI);
		String jsonType = "application/json";
		
		request.addHeader("Content-Type", "application/json;charset=UTF-8");
		request.addHeader("Accept","*/*");
		   

			this.httpResponse = HttpClientBuilder.create().build().execute( request );
		    	
			String Type = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
			   assertEquals(jsonType,Type );
			   System.out.println(this.httpResponse.toString());
				
			BaseUtil.result = EntityUtils.toString(httpResponse.getEntity());
			scenariodef.createNode(new GherkinKeyword("When"),"I Send a GET HTTP request").pass("Pass");
			
				
	}
	@Then("I receive a valid response code 200 from GET tax relief")
	public void check_response() throws ClassNotFoundException, InterruptedException {
		
		
		
	
		System.out.println("Response = " + this.httpResponse.getStatusLine().getStatusCode());
		
		
		if(this.httpResponse.getStatusLine().getStatusCode()!=200) {
			scenariodef.createNode(new GherkinKeyword("Then"),"I receive a valid response code 200 for GET").fail("Fail"); 
			this.scenariodef.log(Status.FAIL, "I am not able to get a valid response");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("Then"),"I receive a valid response code 200 for GET").pass("Pass"); 
			this.scenariodef.log(Status.PASS, "I am able to get a valid response");
			System.out.println("Working Class Hero is Retrieved successfully");    
		}
		
	   
	
	}
	
	@And("\\({int})AC1: I Get the response data")
	public void get_response_data(int tc) throws ClassNotFoundException, InterruptedException {
		
		
		//System.out.println("result1= "+this.result);
		if(BaseUtil.result.isEmpty()) {
			scenariodef.createNode(new GherkinKeyword("And"),"(4)AC1: I Get the response data").fail("Fail");
			this.scenariodef.log(Status.FAIL, "I am not able to get response data");
				
		}else {
			scenariodef.createNode(new GherkinKeyword("And"),"(4)AC1: I Get the response data").pass("Pass");
			this.scenariodef.log(Status.PASS, "I am able to get response data");	
		}
		
		
	   
	}
	
	@Given("I have the response data")
	public void response_data() throws ClassNotFoundException, InterruptedException, ParseException, IOException {
		
	//	System.out.println("result2= "+BaseUtil.result);
		
		if(BaseUtil.result.isEmpty()) {
			scenariodef.createNode(new GherkinKeyword("Given"),"I have the response data").fail("Fail");
			this.scenariodef.log(Status.FAIL, "I don't nhave the response data");
		}else{
			scenariodef.createNode(new GherkinKeyword("Given"),"I have the response data").pass("Pass");
			this.scenariodef.log(Status.PASS, "I have the response data");	
		}
		
	    
	}
	
	@Then("\\({int})AC2: The Natid has to be masked from the 5th character with a dollar \"$\" sign")
	public void nat_id_format(int tc) throws ClassNotFoundException, InterruptedException {
		
		
		for(int i=0;i<BaseUtil.result.length();i++) {
			
			String str=BaseUtil.result;
			JSONArray array = new JSONArray(str); 
			for(int j=0; j < array.length(); j++)   
			{  
				JSONObject object = array.getJSONObject(j);  
				
				String natid = object.getString("natid");
				int length=natid.length()-4;
				 	 
				Pattern pattern = Pattern.compile("^\\d{4}[$]{"+length+"}$");
				  Matcher m = pattern.matcher(natid);
				  ans  = m.matches(); 
				  if(!ans) {
					  scenariodef.createNode(new GherkinKeyword("Then")," (4)AC2: natid is not masked from the 5th character with a dollar \"$\" sign").fail("Fail");
					  this.scenariodef.log(Status.FAIL, "(4)AC2:natid is not masked from 5th character onwards with a $ sign"+natid);
					  break;
				  }
				  
				  
			}
			
			
		}
		if(ans) {
			scenariodef.createNode(new GherkinKeyword("Then"),"(4)AC2: natid is masked from the 5th character with a dollar \"$\" sign").pass("Pass");
			this.scenariodef.log(Status.PASS, "natid is masked from 5th character onwards with a $ sign");
		}
		
	}
	
	
	
	@When("I check the tax relief field value")
	public void tax_relief_formula() throws ClassNotFoundException, InterruptedException, IOException {
		FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scenariodef.createNode(new GherkinKeyword("When"),"The tax relief field value is retrieved based on the formula");

		this.scenariodef.log(Status.PASS, "Tax relief is calculated as expected");
		taxcalculator = new TaxCalculator();
	//	System.out.println("Result of Tax Relief Get "+BaseUtil.result);
		//Read spreadsheet to get input data in ArrayList
		readfile = new ReadFile();
		input = new ArrayList<String>();
		//read file will return the data in an ArrayList format
		input =readfile.readfile(p.getProperty("input_data_file"));
		//feed it into the calculator tax function to get the new arraylist of format natid, name and tax relief
		my_taxRelief= new ArrayList<String>();
		my_taxRelief=taxcalculator.calculate_tax(input);
		
		
		result_str="";
		//removing the first and last '['']' brackets
		for(int i=0;i<BaseUtil.result.length();i++) {
			if(i!=0&&i!=BaseUtil.result.length()-1) {
				result_str +=BaseUtil.result.charAt(i);
			}
				
		}
		
	//	System.out.println("GET API respose "+result_str);
	//	System.out.println("my tax calc "+my_taxRelief);
		
		
	}
	@Then("\\({int})AC3: It should compute correct tax amount as per formula")
	public void round_tax_relief(int tc) throws ClassNotFoundException, InterruptedException, IOException, org.json.simple.parser.ParseException {
		
		
		
		datacomparison = new DataComparison();
		
		//System.out.println("this.result_str"+this.result_str);
		//List<String> list1 = new ArrayList<>(Arrays.asList(this.result_str.split("}")));
		//List<String> list2 = new ArrayList<>();
		//for(int i=0;i<list1.size();i++) {
			
	////		String str=list1.get(i);
		//	str+="}";
		//	list2.add(str);
	//	}
	//	System.out.println("this.my_taxRelief "+ this.my_taxRelief);
		//System.out.println("list2 "+ list2);
		datacomparison.compare_data(this.my_taxRelief,BaseUtil.result);
		if(tax_calc){
			scenariodef.createNode(new GherkinKeyword("Then"),"(4)AC3: It computes correct tax amount as per formula").pass("Pass");
			this.scenariodef.log(Status.PASS, "Tax relief is computed correctly");	
		}else {
			scenariodef.createNode(new GherkinKeyword("Then"),"(4)AC3: It did not compute correct tax amount as per formula as it has "+tax_calc_mismatch_cnt+ " records mismatch for the given data").fail("Fail");
			this.scenariodef.log(Status.FAIL, "It did not compute correct tax amount as per formula as it has "+tax_calc_mismatch_cnt+ " records mismatch for the given data");	
		}
		
	}
	
	@Given("I have the calculated tax relief information")
	public void tax_relief_info() throws ClassNotFoundException, InterruptedException {
		
		scenariodef.createNode(new GherkinKeyword("Given"),"I have retrieved the calculated tax relief information").pass("Pass");

		this.scenariodef.log(Status.PASS, "Retrieved Tax relief info");
	}
	
	@When("\\({int})AC6: Truncate to {int} decimal places if more than {int} dp")
	public void apply_rounding_rule(int tc,int dp1, int dp2) throws ClassNotFoundException, InterruptedException {
		
		
		if(two_decimal_places) {
			scenariodef.createNode(new GherkinKeyword("When"),"(4)AC6: The App Truncates to 2 decimal places if more than 2 dp correctly").pass("Pass");
			this.scenariodef.log(Status.PASS, "Applied rounding rule correctly");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("When"),"(4)AC6: The App did not Truncates to 2 decimal places if more than 2 dp correctly as there are "+round_logic_mismatch_cnt+" mismatch for the given data").fail("Fail");
			this.scenariodef.log(Status.FAIL, "Did not apply rounding rule due to "+round_logic_mismatch_cnt+" mismatch in records for the given data");
		}
	}
	
	@Then("\\({int})AC4: The final relief  amount is of format .00 with zero decimal values due to the rounding rule")
	public void final_tax_amt(int tc) throws ClassNotFoundException, InterruptedException {
		
		
		
		
		if(round_logic) {
			scenariodef.createNode(new GherkinKeyword("Then"),"(4)AC4: The final relief  amount is of format .00 with zero decimal values due to the rounding rule").pass("Pass");
			this.scenariodef.log(Status.PASS, "The final relief  amount is of format $.00 with zero decimal values");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("Then"),"(4)AC4: The final relief  amount is not of format .00 with zero decimal values and rounding rule is not applied").fail("Fail");
			this.scenariodef.log(Status.FAIL, "The final relief amount is not of format $.00 . It has non-zero decimal values");
			
		}
	}
	@When("The tax amount is more than 0.00 and less than 50.00")
	public void tax_amt_GT_2DP() throws ClassNotFoundException, InterruptedException {
		
		
		if(relief_50) {
			scenariodef.createNode(new GherkinKeyword("And")," The tax amount is more than 0.00 and less than 50.00").pass("Pass");
			this.scenariodef.log(Status.PASS, " The tax amount is more than 0.00 and less than 50.00");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("And")," The tax amount is more than 0.00 and less than 50.00").info("Info");
			this.scenariodef.log(Status.INFO, " Cant' determine as the test data does not fall under this category");
		}
	}
	@Then("\\({int})AC5: The final tax amount is 50.00")
	public void tax_trunc_2DP(int tc) throws ClassNotFoundException, InterruptedException {
		
		
		if(relief_50) {
			scenariodef.createNode(new GherkinKeyword("Then"),"(4)AC5: The final tax amount is 50.00 when the amount is between 0.00 and 50.00").pass("Pass");
			this.scenariodef.log(Status.PASS, "(4)AC5: The final tax amount is 50.00 when the amount is between 0.00 and 50.00");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("Then"),"(4)AC5: Cant' determine if the final tax amount is 50.00 when the amount is between 0.00 and 50.00").info("Info");
			this.scenariodef.log(Status.INFO, "(4)AC5:  Cant' determine as the test data does not fall under this category");
		}
		
	}
	@Then("The total tax relief value should be correct")
	public void total_tax_relief() throws ClassNotFoundException, InterruptedException {
		
		
		if(BaseUtil.my_total_tax_relief==BaseUtil.app_total_tax_relief) {
			scenariodef.createNode(new GherkinKeyword("Then"),"The total tax relief value is correct").pass("Pass");
			this.scenariodef.log(Status.PASS, "The total tax relief value is correct");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("Then"),"The total tax relief value is not correct with a difference of $ "+Math.abs(BaseUtil.app_total_tax_relief-BaseUtil.my_total_tax_relief)+".").fail("Fail");
			this.scenariodef.log(Status.FAIL, "The total tax relief value is not correct");
		}
		
	}
	
}
