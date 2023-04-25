package com.aliyaa.assignment.mmt.flightDetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException{

	public FlightNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
