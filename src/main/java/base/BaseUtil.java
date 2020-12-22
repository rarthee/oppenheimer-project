package base;



import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseUtil {

	public static WebDriver driver;
	//The ExtentReports class has multiple overloaded createTest methods some of which support Gherken style, some just the test case etc
	public ExtentReports extent;
	//ExtentTest is used for scenario definition and features. Features call scenario and scenario calls steps
	public static ExtentTest scenariodef;
	public static ExtentTest features;
	public static String result;
	public static boolean tax_calc=true;
	public static boolean round_logic=true;
	public static boolean relief_50=false;
	public static boolean two_decimal_places= false;
	public static int tax_calc_mismatch_cnt=0;
	public static int round_logic_mismatch_cnt=0;
	public static double my_total_tax_relief=0.00;
	public static double app_total_tax_relief=0.00;
	//To store the files in the local machine
	public static String reportlocation = "C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\target\\cucumber-report-html\\";
	
}
