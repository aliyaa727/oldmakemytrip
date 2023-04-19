package com.aliyaa.assignment.mmt.flightDetails.cont;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyaa.assignment.mmt.flightDetails.DTO.FareDTO;
import com.aliyaa.assignment.mmt.flightDetails.DTO.FlightsDTO;
//import com.aliyaa.assignment.mmt.flightDetails.entity.FareDetails;
import com.aliyaa.assignment.mmt.flightDetails.entity.Flights;
import com.aliyaa.assignment.mmt.flightDetails.exceptions.Validations;
import com.aliyaa.assignment.mmt.flightDetails.service.FlightDetailsService;

@RestController
public class FlightDetailsController {
	
	@Autowired(required = false)   
	private FlightDetailsService flightService;    
	
	Validations validations =new Validations();
	
	public FlightDetailsService getFlightService() {
		return flightService;
	}

	public void setFlightService(FlightDetailsService flightService) {
		this.flightService = flightService;
	}
	/*
	@GetMapping("/search/{source}/{destination}/{departureDate}")
	public List<FlightDetailsCreate> findByDestinationandSourceandDepartureDate(@PathVariable String source, 
	@PathVariable("destination") String destination,
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
	public List<Flights> getFlightDetail(@RequestParam(value="pageNumber",defaultValue="1", required=false) 
	Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10", required=false) Integer pageSize)
	{
		return (List<Flights>) flightService.findAllFlights(pageNumber,pageSize);
	}
	
	@GetMapping("/getAllFlights/{flightNumber}")
	public Optional<Flights> findByFlightNumber(@PathVariable int flightNumber)
	{
		
		Optional<Flights> flights=flightService.findById(flightNumber);
	if(!flights.isPresent())
		throw new RuntimeException("Flight number is invalid "+ flightNumber);
	     
		return flights;
	}
	@DeleteMapping("/deleteFlights/{flightNumber}")
	public String deleteByFlightNumber(@PathVariable int flightNumber)
	{
		Optional<Flights> flights=flightService.findById(flightNumber);
		if(!flights.isPresent())
			throw new RuntimeException("Flight number is invalid "+ flightNumber);
		     
		flightService.deleteByFlightNumber(flightNumber);
		return "Deleted flight number" + flightNumber;

	}
	@DeleteMapping("/flightsDelete")
	public String deleteAllFlights(Flights fareDetailsCreate) {
	flightService.deleteAll();
	return "Deleted All flight";
	}
	@GetMapping("/searchFlight")
	public ResponseEntity<List<FlightsDTO>>  findFlights(@RequestParam("source") String source, 
			@RequestParam("destination") String destination ,
			@RequestParam( required =false) LocalDate departureDate, @RequestParam("classType") String classType,
			@RequestParam(required=false) String roundTrip,@RequestParam(required=false) LocalDate returnDate,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String sortingType,@RequestParam(required=false) String departure,
			@RequestParam(required=false) String departureType,
			@RequestParam(value="pageNumber",defaultValue="1", required=false) Integer pageNumber ,
			@RequestParam(value="pageSize",defaultValue="10", required=false) Integer pageSize) throws IOException {
		classType = classType.toLowerCase();
		classType = StringUtils.capitalize(classType);
		validations.sourceAndDestination(source, destination);
		validations.dates(departureDate);
		validations.classValid(classType.toLowerCase());
			validations.trueFalse(departure.toLowerCase());
			validations.trueFalse(sort.toLowerCase());
			validations.trueFalse(roundTrip.toLowerCase());
			validations.paging(pageNumber,pageSize);
		List<Flights> oneWayTripFlights=new ArrayList<>();
		oneWayTripFlights=flightService.searchFlights(source,destination,departureDate,classType);
		
		System.out.println("size after one way filter " + oneWayTripFlights.size());


		if(roundTrip.toLowerCase().equals("true"))
		{
			validations.dates(returnDate);
		validations.departAndArriveDate(departureDate,returnDate);
			List<Flights> roundWayTripFlights=flightService.searchFlights(destination,source,returnDate,classType);
			System.out.println("size after round way filter " + roundWayTripFlights.size());
			
			oneWayTripFlights.addAll(roundWayTripFlights);

			
		}
		
		if(sort.toLowerCase().equals("true")) {
			validations.sortingTypes(sortingType.toLowerCase());
			
			if(sortingType.toLowerCase().equals("duration"))
			{
				
				oneWayTripFlights=flightService.sortFlight1(source,destination,departureDate,classType);
				if(roundTrip.toLowerCase().equals("true"))
				{
					List<Flights> roundWayTripFlights=flightService.sortFlight1(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}
				

			}
			else if(sortingType.toLowerCase().equals("fares"))
				
			{
				oneWayTripFlights=flightService.sortFlight2(source,destination,departureDate,classType);
				if(roundTrip.toLowerCase().equals("true"))
				{
					List<Flights> roundWayTripFlights=flightService.sortFlight2(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}

			}
			
		}
		if(departure.toLowerCase().equals("true")) {
			validations.filter(departureType.toLowerCase());
			
			if(departureType.toLowerCase().equals("morning"))
			{
				oneWayTripFlights=flightService.filterByMorning(source,destination,departureDate,classType);
				//return oneWayTripFlights;
				if(roundTrip.toLowerCase().equals("true"))
				{
					List<Flights> roundWayTripFlights=flightService.filterByMorning(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}
				
			}
			else if(departureType.toLowerCase().equals("evening"))
			{
				oneWayTripFlights=flightService.filterByEvening(source,destination,departureDate,classType);
				//return oneWayTripFlights;
				if(roundTrip.toLowerCase().equals("true"))
				{
					List<Flights> roundWayTripFlights=flightService.filterByEvening(destination,source,returnDate,classType);
					oneWayTripFlights.addAll(roundWayTripFlights);

				}
				
			}
			
		}
		
		System.out.println("size before pagination "+oneWayTripFlights.size());
		oneWayTripFlights=flightService.searchAndPaging(pageSize,pageNumber);
		System.out.println("size after pagination "+oneWayTripFlights.size());
		
		ModelMapper model=new ModelMapper();
		
		List<FlightsDTO> flightsDto = new ArrayList<>();
		
		oneWayTripFlights.forEach(flight -> {
			List<FareDTO> fareDtoList = new ArrayList<>();
			flight.getFareDetails().forEach(fare -> fareDtoList.add(model.map(fare, FareDTO.class)));
			FlightsDTO dto = model.map(flight, FlightsDTO.class);
			dto.setFares(fareDtoList);
			flightsDto.add(dto);
		});
		
		
		
		model.map(oneWayTripFlights, FlightsDTO.class);
	/*	List<FareDTO> fareDTO =  oneWayTripFlights.stream().map(fares -> new FareDTO(
				fares.getFareDetails().stream().
				))*/
		/*List<FlightsDTO> flightsFound = oneWayTripFlights.stream().map(flights-> new FlightsDTO(
																		flights.getAirlines(),
																		flights.getDepartureTime(),
																		flights.getArrivalTime(),
										                               	flights.getDuration()),
				flights.get)
				               */                                     //    .collect(Collectors.toList());
		 /*List<FareDTO> flightss=oneWayTripFlights.stream().map(flights -> new FareDTO(
				flights.getFareDetails().stream().mapToInt(fare()),
				flights.getFareDetails().stream().mapToInt(id()))).collect(Collectors.toList());*/
	//	flightsFound=flightsFound.searchingAndPaging(pageSize,pageNumber);
		return new ResponseEntity<>(flightsDto, HttpStatus.OK);

	}

	/*	public Page<FlightsDTO> pageable(Integer pageNumber)
		{
			Pageable pageable=PageRequest.of(pageNumber, 10);
			return flightService.
		}
*/
		
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
