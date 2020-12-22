package stepDefinition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class TopList_Steps {


		WebDriver driver;
		@Given("^I open browser$")
		public void setup() {
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sahana Rangarajan\\Documents\\Arthee\\chromedriver.exe");
			driver= new ChromeDriver();
			driver.navigate().to("https://google.com");
			driver.manage().window().maximize();
			
		}
		@When("I check for SG market")
		public void login() {
			
			
		}
		
}


