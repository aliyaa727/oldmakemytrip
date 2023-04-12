package com.aliyaa.assignment.mmt.flightDetails.exceptions;

import java.io.IOException;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Validations extends RuntimeException {
	ErrorResponse errorResponse = new ErrorResponse();

	public void departAndArriveDate(LocalDate depart, LocalDate arrive) throws IOException {
		if (depart.isAfter(arrive)) {
			throw new IOException("Enter Valid return date");

		}
	}

	public void classValid(String classType) throws IOException {
//	if(! classType.equals("business") || !(classType.equals("economic")))
//	{
//		throw new IOException("Enter valid class type");
//	}

//	System.out.print(classType);
//	
//	if(!classType.equals("business")) {
//		System.out.print("true for business");
//	}

		if (classType.equals("business") || classType.equals("economy")) {

		} else {
			throw new IOException("Enter valid class type");
		}
	}

	
	public void sortingTypes(String sortType) throws IOException {
		if (sortType.equals("fares") || sortType.equals("duration")) {

		} else {
			throw new IOException("Enter valid sort type");
		}
	}

	public void trueFalse(String roundTrip) throws IOException {
		if (roundTrip.equals("true") || roundTrip.equals("false")) {

		} else {
			throw new IOException(
					"Enter valid type for return date or sorting type or filter type. Only true false is accepted");

		}
	}

	public void filter(String filterType) throws IOException {
		if (filterType.equals("morning") || filterType.equals("evening")) {

		} else {
			throw new IOException("Enter valid filter type");
		}
	}
}