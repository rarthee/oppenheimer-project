package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import base.BaseUtil;

public class UploadCSV_API extends BaseUtil{
	
	
	//This page class has the webelements and the methods that operate on API to upload CSV file
	//This is to test if the API that uploads large file for insertion into the database
	By browse_button=By.xpath("//*[@id=\"operations-calculator-controller-uploadFileUsingPOST\"]/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]/input");
	By try_it_out=By.xpath("//*[@id=\"operations-calculator-controller-uploadFileUsingPOST\"]/div[2]/div/div[1]/div[1]/div[2]/button");
	By execute_btn=By.xpath("//*[@id=\"operations-calculator-controller-uploadFileUsingPOST\"]/div[2]/div/div[2]/button");
	By upload_result=By.xpath("//*[@id=\"operations-calculator-controller-uploadFileUsingPOST\"]/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[1]");
	
	WebElement uploadfile;
	public UploadCSV_API(WebDriver driver) {
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
		String url = p.getProperty("upload_csv_API");
		BaseUtil.driver = new ChromeDriver();
		BaseUtil.driver.navigate().to(url);
		BaseUtil.driver.manage().window().maximize();
		
		
	}
	
	
	public void clicktryoutbtn() throws InterruptedException {
		
		Thread.sleep(5000);
		BaseUtil.driver.findElement(try_it_out).click();
		
	}
	public boolean check_upload_btn() {
		return(BaseUtil.driver.findElement(browse_button).isEnabled());
			
			
		
	}
	public void uploadthefile() throws InterruptedException, FileNotFoundException {
		
		Thread.sleep(5000);
		uploadfile = BaseUtil.driver.findElement(browse_button);
		FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String upload_csv_API_file = p.getProperty("upload_csv_API_file");
		uploadfile.sendKeys(upload_csv_API_file);
		
	}
	
	public void click_execute() {
		BaseUtil.driver.findElement(execute_btn).click();
	}
	public String validate_result() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(5000);
		String result=BaseUtil.driver.findElement(upload_result).getText();
		//System.out.println(result);
		return result;
	}
	
	public void close_driver() {
		BaseUtil.driver.close();
	}
}
