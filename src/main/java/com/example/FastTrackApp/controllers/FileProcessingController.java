package com.example.FastTrackApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FastTrackApp.models.FileRecord;
import com.example.FastTrackApp.services.FileRecordService;


@RestController
@RequestMapping(value="/fileRecords")
public class FileProcessingController {

	@Autowired
	FileRecordService fileRecordService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<FileRecord> getRecord(@PathVariable("id") String id) {
		return fileRecordService.getRecord(id);
	}
}
