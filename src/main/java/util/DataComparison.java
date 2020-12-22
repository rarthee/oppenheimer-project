package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONArray; 


import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import base.BaseUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.PeriodType;

//Compares my calculated tax relief data with the app calculated tax relief data and sets the flags to true or false in
//order to test
public class DataComparison extends BaseUtil{

	
	 
	List<String> tax_relf_list;
	ArrayList<String> input_list;
	List<String> datarow;
	
	String my_name;
	String my_natid;
	String my_relief;
	String app_name;
	String app_natid;
	String app_relief;
	 
	
	
	 JsonObject jsonObj;
	 String formattedstring="";
	 Gson g;
	 JSONParser parser; 
	
	@Test
	public  void compare_data(List<String> my_tax_relief,String result) throws IOException, ParseException {
		FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileWriter fw1 = new  FileWriter(p.getProperty("incorrect_rounding_logic_file"),true);
		PrintWriter out1 = new PrintWriter(fw1);
		FileWriter fw2 = new  FileWriter(p.getProperty("incorrect_tax_calc_file"),true);
		PrintWriter out2 = new PrintWriter(fw2);
		 for(int i=0;i<my_tax_relief.size();i++) {
			
			
			//Get my tax output into json format
			String my_str=my_tax_relief.get(i).toString();
			my_str="["+my_str+"]";
			JSONArray array_my_str = new JSONArray(my_str);
			
			
			//Get response output into json format
			//System.out.println("app_str = "+app_str);
			JSONArray result_app_str = new JSONArray(result); 
			System.out.println("response length "+result_app_str.length());
		 	//System.out.println("response "+result_app_str.get(i));
		 	JSONArray app_str = new JSONArray(result_app_str);
		 	String str1= result_app_str.get(i).toString();
		 	str1="["+str1+"]";
		 	JSONArray array_app_str = new JSONArray(str1);
		
			for(int j=0; j < array_my_str.length(); j++)   
			{   
				
				JSONObject my_object = array_my_str.getJSONObject(j);  
				JSONObject app_object = array_app_str.getJSONObject(j);  
				
				
				my_natid=my_object.getString("natid");  
				my_name=my_object.getString("name");
				my_relief = my_object.getString("relief");
				
				
			    app_natid=app_object.getString("natid");  
				app_name=app_object.getString("name");
				app_relief = app_object.getString("relief");
				System.out.println("my_natid "+my_natid+"my_name "+my_name+"my_relief"+my_relief);
				System.out.println("app_natid "+app_natid+"app_name "+app_name+"app_relief"+app_relief);
				System.out.println(my_natid.equals(app_natid));
				System.out.println(my_name.equals(app_name));
				my_total_tax_relief=my_total_tax_relief+Double.parseDouble(my_relief);
				app_total_tax_relief=app_total_tax_relief+Double.parseDouble(app_relief);
				if(my_natid.equals(app_natid)&&my_name.equals(app_name)) {
					System.out.println("Double.parseDouble(app_relief)"+Double.parseDouble(app_relief));
					System.out.println("Double.parseDouble(my_relief)"+Double.parseDouble(my_relief));
					if((Double.parseDouble(app_relief)==50.00)&&(Double.parseDouble(my_relief)==50.00)) {
						
						relief_50=true;
						
					}
					if(Math.abs((Double.parseDouble(app_relief)-Double.parseDouble(my_relief)))==0.00) {
						continue;
					}else 
					//check if the differene between the system calculated 
					if((Math.abs(Double.parseDouble(app_relief)-Double.parseDouble(my_relief))>0.50)) {
						String contents ="app_natid: "+app_natid+" "+"app_name: "+app_name+" "+"app_relief: "+app_relief;
						out2.println(contents);
						contents="my_natid: "+my_natid+" "+"my_name: "+my_name+" "+"my_relief: "+my_relief;
						tax_calc=false;
						System.out.println("tax calc false with contents "+contents);
						out2.println(contents);
						tax_calc_mismatch_cnt+=1;
						
					}else if(Math.abs((Double.parseDouble(app_relief)-Double.parseDouble(my_relief)))<=0.50) {
						
						round_logic=false;
						String contents ="app_natid: "+app_natid+" "+"app_name: "+app_name+" "+"app_relief: "+app_relief;
						out1.println(contents);
						contents="my_natid: "+my_natid+" "+"my_name: "+my_name+" "+"my_relief: "+my_relief;
						System.out.println("round logic false with contents "+contents);
						out1.println(contents);
						round_logic_mismatch_cnt+=1;
						
					}else {
						round_logic=false;
						tax_calc=false;
					}
						
					
									
					
				}
				
			}
			
			
		}

		 out1.close();
		 out2.close();
	}
}
	

