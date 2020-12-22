package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;


import base.BaseUtil;

public class Tax_Dispense_Page extends BaseUtil{
	
	//This page class has the webelements and the methods that operate on the webelements for the TaxDispense UI

	By dispense_now_btn=By.xpath("//*[@id=\"contents\"]/a[2]");
	By cash_dispensed_txt=By.xpath("//*[@id=\"app\"]/div/main/div/div/div");
	By page_title=By.xpath("/html/body/div/h1");
	By button_color = By.className("btn btn-danger btn-block");
	By total_relief_amt = By.xpath("//*[@id=\"contents\"]/div[3]/div/p");
	

	public Tax_Dispense_Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
	public void openurl() throws InterruptedException, FileNotFoundException {
		
		FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = p.getProperty("url_tax_dispense");
		BaseUtil.driver = new ChromeDriver();
		BaseUtil.driver.navigate().to(url);
		BaseUtil.driver.manage().window().maximize();
		
		
	}
	
	
	public void click_tax_disp_btn() throws InterruptedException {
		
		Thread.sleep(2000);
		BaseUtil.driver.findElement(dispense_now_btn).click();
		Thread.sleep(2000);
	}
	
	public String check_text() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println("BaseUtil.driver.findElement(tax_dispense_button).getText() ");
		Thread.sleep(2000);
		return(BaseUtil.driver.findElement(dispense_now_btn).getText());
			
			
	}
	
	public String check_page_text() throws InterruptedException {
		Thread.sleep(2000);
		return(BaseUtil.driver.findElement(cash_dispensed_txt).getText());
			
			
	}
	public String check_page_title() {
		System.out.println(BaseUtil.driver.getTitle());
		return(BaseUtil.driver.getTitle());
			
			
	}
	public boolean total_amount() {
		String totaltax_value =  BaseUtil.driver.findElement(total_relief_amt).getText();	
		if(totaltax_value.contains("$")) {
			return true;
		}else
		{
			return false;
		}
		
		
	}
	public boolean check_button_color() {
		String button_color = BaseUtil.driver.findElement(dispense_now_btn).getCssValue("background-color");
		//Color will be in RGP
		System.out.println(button_color);
		//Converting it to Hex
		String color_val=Color.fromString(button_color).asHex();
		//check if the color is red
		//Assumed that the red shade is correct
		if(color_val.equals("#dc3545")) {
			System.out.println(color_val);
			return true;
		}else {
			System.out.println(color_val);
			return false;
		}
					
	}
	public void close() {
		
		BaseUtil.driver.quit();
	}
	
}
