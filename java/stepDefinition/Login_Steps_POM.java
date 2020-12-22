package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class Login_Steps_POM {
	WebDriver driver;
	
	@Before("@Smoke,@Regression")
    public void beforeScenario(){
        System.out.println("New scenario begins");
	}
	@After("@Smoke,@Regression")
    public void afterScenario(){
        System.out.println("Scenario Ends");
        driver.close();
	}
	@Given("I am on home page")
	public void i_am_on_home_page() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sahana Rangarajan\\Documents\\Arthee\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.navigate().to("https://google.com");
		driver.manage().window().maximize();
	}
	@And("Click on SignIn button")
	public void click_on_SignIn_button() {
		
		
	}
	@When("^I specify Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void i_specify_Username_as_and_Password_as(String arg1, String arg2) throws InterruptedException{
       // driver.findElement(By.cssSelector("input#login_field")).sendKeys(arg1);
       // driver.findElement(By.cssSelector("input#password")).sendKeys(arg2);
        // Write code here that turns the phrase above into concrete actions
		System.out.println(arg1+' '+arg2);
		LoginPage login = new LoginPage(driver);
		login.clicksearch();
		Thread.sleep(2000);
    }
	@Given("^I open browser$")
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sahana Rangarajan\\Documents\\Arthee\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.navigate().to("https://google.com");
		driver.manage().window().maximize();
		
	}
	@When("I navigate to Login page")
	public void login() {
		
		
	}
	
	@Then("I Validate Home Page Title")
	public void homepagetitle() {
		
		
	}
}
