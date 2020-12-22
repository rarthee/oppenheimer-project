package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
	By txt_username_loginpage=By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input");
	By btn_search_loginpage = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]");
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
	}
	public void enterusername() {
		
		
	}
	public void enterloginname(String login) {
		
		
	}
	public void enterstring(String login) {
		
		
	}
	
	public void clicksearch() {
		
		
	}
}
