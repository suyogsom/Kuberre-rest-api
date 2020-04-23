package com.example.FastTrackApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

class CsvFileGeneration implements Runnable {
	public void run() {
		String NEW_LINE_SEPARATOR = "\n";
		File inputFile = null;
		FileWriter  fileWriter = null;
		CSVFormat csvFileFormat = null;
		CSVPrinter  csvFilePrinter = null;
		Scanner myReader = null;
		
		try {		
			 inputFile = new File("src/main/resources/Equity_euro.px.20190626");
			 fileWriter = new FileWriter("src/main/resources/data.csv");
			 
			 myReader = new Scanner(inputFile);
	      
	    while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String[] records = data.split("\\|");		      
  
	        csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);		       
        
	        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

	        csvFilePrinter.printRecord(new Object[] {records[0], records[23],records[10],records[11],records[12],records[13],records[14],records[15],records[16]});	        	        
	     }
		} catch(Exception e) {
			 myReader.close();
	     try {
			fileWriter.flush();
			fileWriter.close();
			csvFilePrinter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}	
		System.out.println("\n\nData parsed and imported to data.csv file");
  }
}
