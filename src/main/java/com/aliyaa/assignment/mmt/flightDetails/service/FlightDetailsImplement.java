package com.aliyaa.assignment.mmt.flightDetails.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

//import com.aliyaa.assignment.mmt.flightDetails.dao.FareDetailsRepository;
import com.aliyaa.assignment.mmt.flightDetails.dao.FlightDetailsRepository;
import com.aliyaa.assignment.mmt.flightDetails.entity.Flights;
@Service
public class FlightDetailsImplement implements FlightDetailsService {
@Autowired(required = false)
private FlightDetailsRepository flightDetailSerRepo;

public Flights saveFareDetail(Flights flightDetailCreate)

{
	return flightDetailSerRepo.save(flightDetailCreate);
}



@Override
public Flights save(Flights flightDetailsCreate) {
	// TODO Auto-generated method stub
	return flightDetailSerRepo.save(flightDetailsCreate);
}




@Override
public String deleteByFlightNumber(int flightNumber) {
	// TODO Auto-generated method stub
	flightDetailSerRepo.deleteById(flightNumber);
	return "Deleted";
}



@Override
public void deleteAll() {
	// TODO Auto-generated method stub
	flightDetailSerRepo.deleteAll();
	
}



@Override
public Optional<Flights> findByFlightNumber(int flightNumber) {
	// TODO Auto-generated method stub
	return flightDetailSerRepo.findById(flightNumber);
}

@Override
public List<Flights> searchAndPaging(Integer pageSize,Integer pageNumber) {
	// TODO Auto-generated method stub
	Pageable pageable=PageRequest.of(pageNumber, pageSize);
	Page<Flights> pageFlights=flightDetailSerRepo.findAll(pageable);
	List<Flights> allFlights=pageFlights.getContent();
	return  allFlights;
}


@Override
public List<Flights> findAllFlights(Integer pageNumber,Integer pageSize) {
	// TODO Auto-generated method stub
	Pageable pageable=PageRequest.of(pageNumber, pageSize);
	Page<Flights> pageFlights=flightDetailSerRepo.findAll(pageable);
	List<Flights> allFlights=pageFlights.getContent();
	return  allFlights;
}



@Override

public Optional<Flights> findById(int flightNumber) {
	// TODO Auto-generated method stub
	return Optional.empty();
}

public List<Flights>  findBySourceAndDestinationAndDepartureDateAndClassType(
		@Param("source") String source, 
		@Param("destination") String destination ,
		@Param("departureDate") LocalDate departureDate, 
		@Param("classType") String classType){
	return flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassType(source,
			destination,departureDate,classType);


}
@Override
public List<Flights> searchFlights(String source, String destination, LocalDate departureDate, String classType) {
	
List<Flights> flights= flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassType(source, 
		destination, departureDate, classType);
 for(Flights flight:flights)
 {
	 flight.getFareDetails().removeIf(fareDetail -> !fareDetail.getClassType().equals(classType));
	 }
 return flights;
}



@Override
public List<Flights> findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByDuration(
		String source, String destination, LocalDate departureDate, String classType) {
	// TODO Auto-generated method stub
	return flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByDuration(
			source, destination, departureDate, classType);
}



@Override
public List<Flights> sortFlight1(String source, String destination,
		LocalDate departureDate, String classType) {
	// TODO Auto-generated method stub
	return flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByDuration(
			source,destination,departureDate,classType);
}



@Override
public List<Flights> findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByFareDetailsFare(String source,
		String destination, LocalDate departureDate, String classType) {
	// TODO Auto-generated method stub
	return flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByFareDetailsFare(
			source, destination, departureDate, classType);

}



@Override
public List<Flights> sortFlight2(String source, String destination, LocalDate departureDate, String classType) {
	// TODO Auto-generated method stub
	return flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByFareDetailsFare(
			source,destination,departureDate,classType);

}


@Override
public List<Flights> filterByMorning(String source, String destination, LocalDate departureDate, String classType ) {

	LocalTime check;
	LocalTime a=LocalTime.of(05,00,00);
	LocalTime b=LocalTime.of(12,00,00);
List<Flights> flights= flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassType(
		source, destination, departureDate, classType);
List<Flights> newFlights=new ArrayList<>();

for(Flights flight:flights)
 { 
		check=flight.getDepartureTime();
    if(check.isAfter(a) && check.isBefore(b))
    
    {     newFlights.add(flight);
    	//FlightDetailsImplement.removeFlight(flights,flight);
    }
 }
 return newFlights;
}


@Override
public List<Flights> filterByEvening(String source, String destination, LocalDate departureDate, String classType) {
	LocalTime check;
	LocalTime a=LocalTime.of(17,59,59);
	LocalTime b=LocalTime.of(23,59,59);
//	Pageable pageable=PageRequest.of(1, 10);
List<Flights> flights= flightDetailSerRepo.findBySourceAndDestinationAndDepartureDateAndFareDetailsClassType(
		source, destination, departureDate, classType);
List<Flights> newFlights = new ArrayList<>();
for(Flights flight:flights)
 {
	 check=flight.getDepartureTime();
    if(check.isAfter(a) && check.isBefore(b))
    
    {
    	newFlights.add(flight);
    }
    
 }
 return newFlights;
}




}

/*
private static void removeFlight(List<Flights> flights, Flights flight) {
	
	flights.remove(flight);
	// TODO Auto-generated method stub
	
}
8?

}
/*

@Override
public List<FlightDetailsCreate> findByDestinationandSourceandDepartureDate(
@PathVariable String source, @PathVariable("destination") String destination,
		@PathVariable("departureDate") LocalDate departureDate) {
	// TODO Auto-generated method stub
	return flightDetailSerRepo.findByDestinationandSourceandDepartueDate(source, destination,departureDate);
}

*/
/*
@Override
public List<FlightDetailsCreate> findByDestinationandSourceandDepartueDate(String source, String destination,
		LocalDate departureDate) {
	// TODO Auto-generated method stub
	return null;
}*/



