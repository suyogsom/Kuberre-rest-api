package com.example.FastTrackApp.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.FastTrackApp.exceptions.BlankInputException;
import com.example.FastTrackApp.exceptions.ResourceNotFoundException;
import com.example.FastTrackApp.exceptions.WrongInputException;
import com.example.FastTrackApp.models.ExceptionResponse;


@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "resource with this ID does not exist", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); 
	  }
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleWrongInputException(WrongInputException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "incorrect input format", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  } 
	  
	  @ExceptionHandler
	  public final ResponseEntity<ExceptionResponse> handleBlankInputException(BlankInputException ex, WebRequest request) { 
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "incorrect input format", "path is " + ((ServletWebRequest)request).getRequest().getRequestURL().toString());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); 
	  }
}

