package com.aliyaa.assignment.mmt.flightDetails.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import com.aliyaa.assignment.mmt.flightDetails.entity.Flights;


public interface FlightDetailsService {

	List<Flights> findAllFlights();

	Flights save(Flights flightDetailsCreate);

	void deleteAll();

	public String deleteByFlightNumber(int flightNumber);

	Optional<?> findByFlightNumber(int flightNumber);

	Optional<Flights> findById(int flightNum);
	//public List<FlightDetailsCreate> findByDestinationandSourceandDepartureDate(String destination, String source,LocalDate departureDate);

	List<Flights> findBySourceAndDestinationAndDepartureDateAndClassType(String source, String destination,
			LocalDate departureDate, String classType);
	List<Flights> searchFlights(String source, String destination, LocalDate departureDate, String classType);

	List<Flights> findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByDuration(String source, String destination,
			LocalDate departureDate, String classType);
    List<Flights> sortFlight1(String source, String destination,
		LocalDate departureDate, String classType);
    List<Flights> findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByFareDetailsFare(String source, String destination,
		LocalDate departureDate, String classType);
    List<Flights> sortFlight2(String source, String destination,

		LocalDate departureDate, String classType);
    List<Flights> filterByMorning(String source, String destination,
		LocalDate departureDate, String classType);
    List<Flights> filterByEvening(String source, String destination,
		LocalDate departureDate, String classType);

	//List<FlightDetailsCreate> findByDestinationandSourceandDepartueDate(String source, String destination,LocalDate departureDate);
}
