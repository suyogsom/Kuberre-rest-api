package com.example.FastTrackApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileProcessingApplication {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessingApplication.class);
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FileProcessingApplication.class, args);
		System.out.println("\n\nSuccess!! Application started");
		
		LOGGER.error("Messages logged at ERROR level");
		LOGGER.warn("Messages logged at WARN level");
		LOGGER.info("Messages logged at INFO level");
		LOGGER.debug("Messages logged at DEBUG level");

		csvFileGeneration();
	}	
	
	public static void csvFileGeneration() throws Exception{
		CsvFileGeneration fileGeneration = new CsvFileGeneration();
		Thread thread1 = new Thread(fileGeneration);
		thread1.start();
		thread1.join();
	}			
}









