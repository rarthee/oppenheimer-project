package util;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;  
import org.json.JSONArray; 
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


//Calculates tax relief based on the formula.
//Input is the data for heroes . It calculates Age, variable pay , rounds off the decimal places 
//If the relief value is less than 50, it sets the value to 50.00
public class TaxCalculator {

	XSSFWorkbook myworkbook;
	XSSFSheet mysheet; 
	FileInputStream fs ;
	 
	List<String> tax_relf_list;
	ArrayList<String> input_list;
	List<String> datarow;
	double bonus;
	String tax;
	String birthday;
	String gender;
	String name;
	String salary;
	String natid;
	 List<String> heroes;
	 List<String> my_taxRelief;
	 double tax_relief;
	 String tax_relief_new;
	 double variable ;
	 JsonObject jsonObj;
	 String formattedstring="";
	 Gson g;
	@Test
	public List<String> calculate_tax(List<String> heroes) throws IOException {
		//System.out.println("Heroes "+heroes);
		my_taxRelief = new ArrayList<String>();
		
		for(int i=0;i<heroes.size();i++) {
			
			
			String str=heroes.get(i).toString();
			str="["+str+"]";
		
			JSONArray array = new JSONArray(str); 
			for(int j=0; j < array.length(); j++)   
			{  
				JSONObject object = array.getJSONObject(j);  
				salary=object.getString("salary");  
				name=object.getString("name");
				gender=object.getString("gender");
				birthday=object.getString("birthday");
				tax=object.getString("tax");
				natid = object.getString("natid");
				
			    //Age calculation
			    LocalDate today = LocalDate.now();   
			    //Today's date
			    Month monthString = null;
			    int YYYY=Integer.parseInt(birthday.substring(4,8));
			   	int MM = Integer.parseInt(birthday.substring(2,4));
			    int DD = Integer.parseInt(birthday.substring(0,2));
			    
			    switch (MM) {
	            case 1:  monthString = Month.JANUARY;
	                     break;
	            case 2:  monthString = Month.FEBRUARY;
	                     break;
	            case 3:  monthString = Month.MARCH;
	                     break;
	            case 4:  monthString = Month.APRIL;
	                     break;
	            case 5:  monthString = Month.MAY;
	                     break;
	            case 6:  monthString = Month.JUNE;
	                     break;
	            case 7:  monthString = Month.JULY;
	                     break;
	            case 8:  monthString = Month.AUGUST;
	                     break;
	            case 9:  monthString = Month.SEPTEMBER;
	                     break;
	            case 10: monthString = Month.OCTOBER;
	                     break;
	            case 11: monthString = Month.NOVEMBER;
	                     break;
	            case 12: monthString = Month.DECEMBER;
	                     break;
	           
			    }
			    LocalDate birthday = LocalDate.of(YYYY, monthString,DD);
			    Period p = Period.between(birthday, today);
			
			    //calculate bonus
			    if(gender.equals("F")) {
			    	
			    	bonus = 500;
			    }
			    else {
			    	bonus = 0;
			    }
			    
			    //calculate variable
			            
			     if(p.getYears()<18) {
			    	
			    	variable = 1;
			     }else if((p.getYears()==18)&&(p.getMonths()==0)&&(p.getDays()==0)) {
			    	variable = 1;
			    	
			     }else if(p.getYears()<35) {
			    	variable = 0.8;
			     }else if(p.getYears()==35&&((p.getMonths()==0)&&(p.getDays()==0))) {
			    	
			    	
			    		variable = 0.8;
			    
			     }else if(p.getYears()<50) {
			    	variable = 0.5;
			     }else if(p.getYears()==50&&((p.getMonths()==0)&&(p.getDays()==0))) {
			    	
			    	
			    		variable = 0.5;
			  
			     }else if(p.getYears()<75) {
			    	variable = 0.367;
			     }else if(p.getYears()==75&&((p.getMonths()==0)&&(p.getDays()==0))) {
			    	
			    
			    		variable = 0.367;
			    
			     }	else {
			    	
			    	variable = 0.05;
			     }
			    
			     tax_relief=((Double.parseDouble((salary))-Double.parseDouble(tax))*variable)+bonus;
			   
			     //if tax is more than 0.00 and less than 50.00, tax is 50.00
				 if(tax_relief>=0.00 && tax_relief<=50.00) {
					DecimalFormat df = new DecimalFormat("#.00"); 
					df.setMaximumFractionDigits(2);
					tax_relief_new=df.format(50.00);
					tax_relief=Double.parseDouble(tax_relief_new);
					
				 }
				 else
				 {
				 ///Tax relief truncate decimal to 2 decimal places
				    tax_relief=tax_relief*100;
				    tax_relief=Math.floor(tax_relief);
				    tax_relief=tax_relief/100;
				
				 }
			     //format natid with first 4 characters as digits and the rest with $ symbols
				 // Split into to 2 strings.
				
				 String lastString = natid.substring(4,natid.length());
				 String firstString = natid.substring(0, 4);
				 formattedstring="";
				// System.out.println("First string "+firstString);
				// System.out.println("Last string "+lastString);
			    
				 // Format second string and result is concatenated with first.
				 for(int k=0;k<lastString.length();k++) {
					 formattedstring+="$";
			    	
				 }
				 firstString+=formattedstring;
				 //String formattedLastString = String.format("$", lastString);
			   
				 natid=firstString;
				 formattedstring="";
		  
				 tax_relief=Math.round(tax_relief);
			 
				 my_taxRelief.add("{\"natid\":"+'"'+ natid +'"'+",\"name\":"+'"'+name+'"'+",\"relief\":"+'"'+tax_relief+"0"+'"'+"}");
			    
			}
		}
		
		
		return my_taxRelief;  
	}
	

}
