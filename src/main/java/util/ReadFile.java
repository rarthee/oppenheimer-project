package util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Reads a spreadsheet and returns data in the form of an Array List of String
public class ReadFile {

	XSSFWorkbook myworkbook;
	XSSFSheet mysheet; 
	FileInputStream fs ;
	List<String> datarow;
    List<String> body;
	
	ArrayList<String> fieldsArrayList;
	int rowcount;
	Row row;
	Cell cell;
	Iterator<Cell> celliterator;
    List<String> header;

	public List<String> readfile(String filename) throws IOException {
		List<List<String>> listOfLists = new ArrayList<List<String>>();
		
		
		//fs = new FileInputStream("C:\\Users\\Sahana Rangarajan\\Desktop\\THE OPPENHEIMER PROJECT\\inputdata.xls");
		fs = new FileInputStream(filename);
		myworkbook = new XSSFWorkbook(fs);
		mysheet = myworkbook.getSheetAt(0);
		rowcount = mysheet.getLastRowNum();
		 Iterator<Row> rowIterator = mysheet.iterator();
         while (rowIterator.hasNext()) 
         {
             Row row = rowIterator.next();
             //For each row, iterate through all the columns
             Iterator<Cell> cellIterator = row.cellIterator();
             fieldsArrayList = new ArrayList<String>();
             while (cellIterator.hasNext()) 
             {
                 Cell cell = cellIterator.next();
                 //Check the cell type and format accordingly
                 switch (cell.getCellType()) 
                 {
                     case Cell.CELL_TYPE_NUMERIC:
                         System.out.print(cell.getNumericCellValue());
                         break;
                     case Cell.CELL_TYPE_STRING:
                    	 fieldsArrayList.add((cell.getStringCellValue()));
                         break;
                 }
               
             }
             listOfLists.add(fieldsArrayList);
             
         }
         
        /* for(int i=0;i<listOfLists.size();i++) {
        	 System.out.println(listOfLists.get(i));	 
         }*/
    
         header = new ArrayList<String>();
         header.addAll(listOfLists.get(0));
         int col_len= header.size();
        // System.out.println("header "+header);
        // System.out.println("listOfLists "+listOfLists);	
         body=new ArrayList<String>();
         //starting from 2nd row leaving the header
         for(int i=1;i<listOfLists.size();i++) {
        	 datarow = new ArrayList<String>();
        	 datarow.addAll(listOfLists.get(i));
        	// System.out.println("datarow "+ datarow);
        	body.add("{ \"birthday\":"+'"'+ datarow.get(4) +'"'+",\"gender\":"+'"'+ datarow.get(2)+'"'+",\"name\":"+'"'+ datarow.get(1)+'"'+",\"natid\":"+'"'+ datarow.get(0)+'"'+",\"salary\":"+ '"'+datarow.get(3)+'"'+",\"tax\": "+ '"'+datarow.get(5)+'"'+"}"); 
        	//System.out.println(body);
         }
         
         //System.out.println("body= "+body);
         return(body);
	}
}	
