package com.aliyaa.assignment.mmt.flightDetails.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FlightsExceptionHandler  {
	
	@ExceptionHandler
	   public ResponseEntity<ErrorResponse> exception(RuntimeException exception) {
		ErrorResponse er=new ErrorResponse();
		er.setMessage(exception.getMessage());
	      return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
	   
	}
	@ExceptionHandler
	   public ResponseEntity<ErrorResponse> exception(IOException exception) {
		ErrorResponse er=new ErrorResponse();
		er.setMessage(exception.getMessage());
	      return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
	   
	}
}
