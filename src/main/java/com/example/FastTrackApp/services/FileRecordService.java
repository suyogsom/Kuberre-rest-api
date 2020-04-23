package com.example.FastTrackApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FastTrackApp.exceptions.BlankInputException;
import com.example.FastTrackApp.exceptions.WrongInputException;
import com.example.FastTrackApp.models.FileRecord;
import com.example.FastTrackApp.repos.FileRecordRepo;

@Service
public class FileRecordService {

	@Autowired
	FileRecordRepo fileRepo; 

	public ResponseEntity<FileRecord> getRecord(String id) {		
		if(availabilityCheck(id)) {
			return new ResponseEntity<>(fileRepo.findRecordByIDBBUNIQUE(id), HttpStatus.OK);
		} 
		else if(id.charAt(0)!='E' && id.charAt(1)!='Q') {
			throw new WrongInputException();
		}
		else if(id.trim().length()==0) {
			throw new BlankInputException();
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
		}	
	}
	
	public boolean availabilityCheck(String id) {		
		return fileRepo.findRecordByIDBBUNIQUE(id).getIDBBUNIQUE().length()== 0 ? false : true;
	}
}
