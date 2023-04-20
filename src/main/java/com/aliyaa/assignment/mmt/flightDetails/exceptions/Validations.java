package com.aliyaa.assignment.mmt.flightDetails.exceptions;

import java.io.IOException;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Validations extends RuntimeException {
	ErrorResponse errorResponse = new ErrorResponse();

	public void departAndArriveDate(LocalDate depart, LocalDate arrive) throws IOException {
		if (depart.isAfter(arrive)) {
			throw new IOException("Enter Valid return date.");

		}
	}

	public void classValid(String classType) throws IOException {

		if (classType.equals("business") || classType.equals("economy")) {

		} else {
			throw new IOException("Enter valid class type. Class type can only be Business or economy.");
		}
	}

	
	public void sortingTypes(String sortType) throws IOException {
		if (sortType.equals("fares") || sortType.equals("duration")) {

		} else {
			throw new IOException("Enter valid sort type. Sort type can only be fare or duration.");
		}
	}
	public void sortingTypeForBooking(String sortingType) throws IOException {
		if (sortingType.equals("fares") || sortingType.equals("bookingtime")) {

		} else {
			throw new IOException("Enter valid sort type. Sort type can only be fare or bookingtime.");
		}
	}
	public void trueFalse(String roundTrip) throws IOException {
		if (roundTrip.equals("true") || roundTrip.equals("false")) {

		} else {
			throw new IOException(
					"Enter valid type for return date or sorting type or filter type. Only true false is accepted.");

		}
	}
	public void dates(LocalDate date) throws IOException
	{
		
		if(date==null )
		{
			
			throw new IOException("Enter the dates. Do not leave an empty field");
		}
	}

	public void filter(String filterType) throws IOException {
		if (filterType.equals("morning") || filterType.equals("evening")) {

		} else {
			throw new IOException("Enter valid filter type. Filter type can only be morning or evening.");
		}
	}
	
	public void sourceAndDestination(String source, String destination) throws IOException
	{
		if(source.isEmpty() || destination.isEmpty())
		{
			throw new IOException("Enter the source and destination. Do not leave an empty field");
		}
	}

	public void paging(Integer pageNumber, Integer pageSize) throws IOException {
		// TODO Auto-generated method stub
		if(pageNumber<0)
		{
			throw new IOException("Enter valid page Number. It can not be negative.");
			
		}
		if(pageSize<1)
		{
			throw new IOException("Enter valid page size. It can not be negative.");
			
		}	
	}

	public void inputValidations(String source, String destination,
			LocalDate departureDate, String classType,
			String departure, String sort,
			String roundTrip, Integer pageNumber,
			Integer pageSize) throws IOException {
		
		// TODO Auto-generated method stub
		sourceAndDestination(source, destination);
	dates(departureDate);
	classValid(classType);
			trueFalse(departure);
			trueFalse(sort);
	trueFalse(roundTrip);
		paging(pageNumber,pageSize);
		
		
		
		
	}

	public void inputForBookingDetails(String sort, String sortingType) throws IOException {
		// TODO Auto-generated method stub
		trueFalse(sort);
		sortingTypeForBooking(sortingType);
		
		
	}
}