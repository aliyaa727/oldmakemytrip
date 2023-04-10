package com.aliyaa.assignment.mmt.flightDetails.cont;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyaa.assignment.mmt.flightDetails.DTO.FlightsDTO;
import com.aliyaa.assignment.mmt.flightDetails.entity.FareDetails;
//import com.aliyaa.assignment.mmt.flightDetails.entity.FareDetails;
import com.aliyaa.assignment.mmt.flightDetails.entity.Flights;
import com.aliyaa.assignment.mmt.flightDetails.service.FlightDetailsService;

@RestController
public class FlightDetailsController {
	
	@Autowired(required = false)   
	private FlightDetailsService flightService;    
	
	public FlightDetailsService getFlightService() {
		return flightService;
	}

	public void setFlightService(FlightDetailsService flightService) {
		this.flightService = flightService;
	}
	/*
	@GetMapping("/search/{source}/{destination}/{departureDate}")
	public List<FlightDetailsCreate> findByDestinationandSourceandDepartureDate(@PathVariable String source, @PathVariable("destination") String destination,
			@PathVariable("departureDate") LocalDate departureDate) {
		// TODO Auto-generated method stub
		return flightService.findByDestinationandSourceandDepartueDate(source, destination,departureDate);
	}
	*/

	
	
	
	@PostMapping("/flightSave")
	public Flights insertFlight(@RequestBody Flights flightDetailsCreate) {
		return flightService.save(flightDetailsCreate);
	
	}
	@PutMapping("/flightUpdate")
	public String updateFlight(@RequestBody Flights flightDetailsCreate) {
		flightService.save(flightDetailsCreate);
		return "Updated";
	}
	public FlightDetailsController(
			FlightDetailsService flightService) {
		super();
		this.flightService = flightService;
	}

	@GetMapping("/getAllFlights")
	public List<Flights> getFlightDetail()
	{
		return (List<Flights>) flightService.findAllFlights();
	}
	
	@GetMapping("/getAllFlights/{flightNumber}")
	public Optional<Flights> findByFlightNumber(@PathVariable int flightNumber)
	{
		return flightService.findById(flightNumber);
	}
	@DeleteMapping("/deleteFlights/{flightNumber}")
	public String deleteByFlightNumber(@PathVariable int flightNumber)
	{
		flightService.deleteByFlightNumber(flightNumber);
		return "Deleted flight number" + flightNumber;

	}
	@DeleteMapping("/flightsDelete")
	public String deleteAllFlights(Flights fareDetailsCreate) {
	flightService.deleteAll();
	return "Deleted All flight";
	}
	@GetMapping("/searchFlight")
	public List<FlightsDTO>  findFlights(@RequestParam("source") String source, @RequestParam("destination") String destination ,
			@RequestParam("departureDate") LocalDate departureDate, @RequestParam("classType") String classType,
			@RequestParam Boolean roundTrip ,@RequestParam(required=false) LocalDate returnDate,@RequestParam Boolean sort,
			@RequestParam(required=false) String sortingType,@RequestParam Boolean departure, @RequestParam(required=false) String departureType ){
		
		List<Flights> oneWayTripFlights=new ArrayList<>();
		oneWayTripFlights=flightService.searchFlights(source,destination,departureDate,classType);


		if(roundTrip==true)
		{
			List<Flights> roundWayTripFlights=flightService.searchFlights(destination,source,returnDate,classType);
			oneWayTripFlights.addAll(roundWayTripFlights);

			
		}
		
		if(sort==true) {
			if(sortingType.equals("duration"))
			{
				
				oneWayTripFlights=flightService.sortFlight1(source,destination,departureDate,classType);
				if(roundTrip==true)
				{
					List<Flights> roundWayTripFlights=flightService.sortFlight1(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}
				

			}
			else
			{
				oneWayTripFlights=flightService.sortFlight2(source,destination,departureDate,classType);
				if(roundTrip==true)
				{
					List<Flights> roundWayTripFlights=flightService.sortFlight2(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}

			}
			
		}
		if(departure==true) {
		
			if(departureType.equals("morning"))
			{
				oneWayTripFlights=flightService.filterByMorning(source,destination,departureDate,classType);
				//return oneWayTripFlights;
				if(roundTrip==true)
				{
					List<Flights> roundWayTripFlights=flightService.filterByMorning(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}
				
			}
			else
			{
				oneWayTripFlights=flightService.filterByEvening(source,destination,departureDate,classType);
				//return oneWayTripFlights;
				if(roundTrip==true)
				{
					List<Flights> roundWayTripFlights=flightService.filterByEvening(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}
				
			}
		}
		return  oneWayTripFlights.stream().map(flights-> new FlightsDTO(
				flights.getAirlines(),
				flights.getDepartureTime(),
				flights.getArrivalTime(),
				flights.getDuration(),
				flights.getFareDetails().stream().mapToInt(FareDetails::getFare)
				)).collect(Collectors.toList())
				;
		
		

	}

		

		
		@GetMapping("/search")	
	public String search(@RequestParam String source, 
			@RequestParam String destination,
			@RequestParam String classType,
			@RequestParam(value = "departureDate") LocalDate departureDate,
			@RequestParam(value = "arrivalDate") LocalDate arrivalDate,
			@RequestParam int numberOfTravellers)
	{
		//LocalDate departureD=(LocalDate) formatter.parse(departureDate);
	//	LocalDate arrivalD=(LocalDate) formatter.parse(arrivalDate);
	return source+" "+destination+" "+classType+" "+ departureDate +" " +arrivalDate + " "+numberOfTravellers;
	
	}


}
