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

public class UploadCSV_UI extends BaseUtil{
	
	

	By browse_btn=By.xpath("//*[@id=\"contents\"]/div[1]/div[2]/input");
	By upload_csv_btn = By.xpath("//*[@id=\"contents\"]/div[1]/div[2]/input");
	By tax_relief_btn = By.xpath("//*[@id=\"contents\"]/button[1]");
	By visit_swagger = By.xpath("//*[@id=\"contents\"]/a[1]");
	
	WebElement uploadfile;
	public UploadCSV_UI(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
	
	public void openapplication() throws InterruptedException, FileNotFoundException {
		FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String app = p.getProperty("upload_csv_UI");
		BaseUtil.driver = new ChromeDriver();
		BaseUtil.driver.navigate().to(app);
		BaseUtil.driver.manage().window().maximize();
		Thread.sleep(5000);
		
	}
	public void browsebutton() throws InterruptedException {
		BaseUtil.driver.findElement(browse_btn);
		Thread.sleep(5000);
		
	}
	public void uploadcsvbtn() throws InterruptedException {
		
		Thread.sleep(5000);
		BaseUtil.driver.findElement(upload_csv_btn).click();
		
	}
	public boolean upload_csv_file() throws FileNotFoundException {
		if(BaseUtil.driver.findElement(upload_csv_btn).isEnabled()){
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,250)", "");
			FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
			Properties p = new Properties();
			try {
				p.load(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BaseUtil.driver.findElement(upload_csv_btn).sendKeys(p.getProperty("upload_csv_UI_file"));
			
			return(true);
			
		}else
		{
			return(false);
		}
		
	}
	public void refresh_tax_lable() throws InterruptedException {
		
		Thread.sleep(5000);
		BaseUtil.driver.findElement(tax_relief_btn).click();
	    		
	}
	
	public void visit_swagger() {
		BaseUtil.driver.findElement(visit_swagger).click();
	}
	
	public void close_driver() {
		BaseUtil.driver.close();
	}
	
}
