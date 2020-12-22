package util;

import java.io.BufferedReader;

import java.io.Reader;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.nio.file.Files;
import java.nio.file.Paths;

//Reads a csv file based on header and returns the Array list of String
public class CSVRowValues {
	List<String> fieldsArrayList;
	String CSV_FILE_PATH;
	int count=0; 
	public Map<String, Integer> readheader() throws IOException {
		FileReader reader=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String CSV_FILE_PATH=p.getProperty("upload_csv_UI_file");
		BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH));
		CSVParser parser = CSVParser.parse(br, CSVFormat.EXCEL.withFirstRecordAsHeader());
		List<CSVRecord> headers =  parser.getRecords();
		System.out.println(headers.get(0));
		return parser.getHeaderMap();
	}
	
	public List<String> readrows(int rownum) throws IOException {
		
		FileReader reader1=new FileReader("C:\\Users\\Sahana Rangarajan\\eclipse-workspace\\cucumber_test\\config.properties");  
		Properties p = new Properties();
		try {
			p.load(reader1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String CSV_FILE_PATH=p.getProperty("upload_csv_UI_file");
		Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
		
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("natid", "name", "gender", "salary","birthday", "tax")
                .withIgnoreHeaderCase()
                .withTrim());
		fieldsArrayList = new ArrayList<String>();
		 for (CSVRecord csvRecord : csvParser) {
			
			 if(rownum==count) {
             // Accessing values by the names assigned to each column
			
			 String natid = csvRecord.get("natid");
             String name = csvRecord.get("name");
             String gender = csvRecord.get("gender");
             String salary = csvRecord.get("salary");
             String birthday = csvRecord.get("birthday");
             String tax = csvRecord.get("tax");
             fieldsArrayList.add(natid);
             fieldsArrayList.add(name);
             fieldsArrayList.add(gender);
             fieldsArrayList.add(salary);
             fieldsArrayList.add(birthday);
             fieldsArrayList.add(tax);
			 }
			 count++;
		 }
			 
		
		 return fieldsArrayList;
	}
		
		 
}	
