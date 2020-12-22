package stepDefinition;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import base.BaseUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Tax_Dispense_Page;


public class Tax_Dispense_Steps  extends BaseUtil {
	WebDriver driver;
	
	private BaseUtil base;
	Tax_Dispense_Page tax_disp;
	Scenario scenario;
	
	
	public Tax_Dispense_Steps(BaseUtil base) {
		this.base=base;
	}
	
	public Tax_Dispense_Steps() {
	
	}

	
	@Given("^I am on the tax relief UI page$")
	public void I_Open_the_url() throws ClassNotFoundException, IOException, InterruptedException {
		
		
		tax_disp = new Tax_Dispense_Page(driver);
		tax_disp.openurl();
		scenariodef.createNode(new GherkinKeyword("Given"),"I am on the tax relief UI page").pass("Pass");
	}
	
	
	@And("\\({int})AC2: The text on the button must be exactly \"Dispense Now\"")
	public void Dispese_Button(int tc) throws ClassNotFoundException, InterruptedException {
		
		
		tax_disp = new Tax_Dispense_Page(driver);
		
		String disptext=tax_disp.check_text();
		System.out.println("Dispense text "+disptext);
		if(disptext.contains("Dispense Now")) {
			scenariodef.createNode(new GherkinKeyword("And"),"(5)AC2: The text on the button must be exactly \"Dispense Now\"").pass("Pass");
			this.scenariodef.log(Status.PASS, "The text on the button is exactly \"Dispense Now\"");
			
		}else
		{
			scenariodef.createNode(new GherkinKeyword("And"),"(5)AC2: The text on the button must be exactly \"Dispense Now\"").fail("Fail");
			this.scenariodef.log(Status.FAIL, "The text on the button is not as expected");
		}
	}
	
	@And("I click on the button")
	public void click_tax_button() throws ClassNotFoundException, InterruptedException {
		
		
		tax_disp = new Tax_Dispense_Page(driver);
		tax_disp.click_tax_disp_btn();
		scenariodef.createNode(new GherkinKeyword("And"),"(5)AC1: I can click on the button").pass("Pass");
		this.scenariodef.log(Status.PASS, "I am able to click the button 'Cash Dispensed'");
	}
	
	@When("\\({int})AC1: The button must be Red color")
	public void button_color(int tc) throws ClassNotFoundException, InterruptedException {
		
		
		tax_disp = new Tax_Dispense_Page(driver);
		boolean color=tax_disp.check_button_color();
		if(color) {
			scenariodef.createNode(new GherkinKeyword("When"),"(5)AC1: The button must be Red color").pass("Pass");
			this.scenariodef.log(Status.PASS, "Color of the Cash Dispense button is red");
		}else
		{
			scenariodef.createNode(new GherkinKeyword("When"),"(5)AC1: The button must be Red color").fail("Fail");
			this.scenariodef.log(Status.FAIL, "Color of the Cash Dispense button is not red");
		}
	}
	@And("Total Tax Relief amount should display with format $NNNN.00")
	public void total_relief_amt() throws ClassNotFoundException, InterruptedException {
		tax_disp = new Tax_Dispense_Page(driver);
	
		
		if(tax_disp.total_amount()) {
			scenariodef.createNode(new GherkinKeyword("And"),"Total Tax Relief amount should display with format $NNNN.00").pass("Pass");
			this.scenariodef.log(Status.PASS, "Total Tax Relief amount displays with format $NNNN.00");
		}else {
			
			scenariodef.createNode(new GherkinKeyword("And"),"Total Tax Relief amount should display with format $NNNN.00").fail("Fail");
			this.scenariodef.log(Status.FAIL, "Total Tax Relief amount is not of  format $NNNN.00");
		}
	}
	@Then("\\({int})AC3: It should direct me to a page with a text that says \"Cash dispensed\"")
public void page_check(int tc) throws ClassNotFoundException, InterruptedException {
		
		
		tax_disp = new Tax_Dispense_Page(driver);
		String pagetext =tax_disp.check_page_text();
		System.out.println("Text "+pagetext);
		if(pagetext.equals("Cash dispensed")) {
			scenariodef.createNode(new GherkinKeyword("Then"),"(5)AC3: It should direct me to a page with a text that says \"Cash dispensed\"").pass("Pass");
			this.scenariodef.log(Status.PASS, "I am directed to the correct page that displays text 'Cash Dispensed'");
			
		}
		else {
			scenariodef.createNode(new GherkinKeyword("Then"),"(5)AC3: It should direct me to a page with a text that says \"Cash dispensed\"").fail("Fail");
			this.scenariodef.log(Status.FAIL, "I am not directed to the correct page that displays text 'Cash Dispensed'");
			
		}
	}
	
	@And("I should be on page with title \"Dispense\"")
	public void disp_page_title() throws ClassNotFoundException, InterruptedException {
			
			
			tax_disp = new Tax_Dispense_Page(driver);
			String title =tax_disp.check_page_title();
			System.out.println("Title "+title);
			if(title.equals("Dispense!!")) {
				scenariodef.createNode(new GherkinKeyword("And"),"I should be on page with title Dispense").pass("Pass");
				this.scenariodef.log(Status.PASS, "I am directed to the correct page with title 'Cash Dispensed'");
				
			}
			else {
				scenariodef.createNode(new GherkinKeyword("And"),"I should be on page with title Dispense").fail("Fail");
				this.scenariodef.log(Status.FAIL, "I am not directed to the correct page with title 'Cash Dispensed'");
				
			}
			//driver close
			tax_disp.close();
		}
	
	
	
}
