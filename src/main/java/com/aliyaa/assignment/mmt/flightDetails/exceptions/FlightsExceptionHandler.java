package com.aliyaa.assignment.mmt.flightDetails.exceptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	@ExceptionHandler
	public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {
	Map<String, String> response = new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach((error) -> {
	String fieldName = ((FieldError) error).getField();
	String message = error.getDefaultMessage();
	response.put(fieldName, message);
	});
	return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
	}
}
