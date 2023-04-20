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
import com.aliyaa.assignment.mmt.flightDetails.service.FlightDetailsService;

@RestController
public class FlightDetailsController {

	@Autowired(required = false)
	private FlightDetailsService flightService;

	// method to save the flights
	@PostMapping("/flightSave")
	public Flights insertFlight(@RequestBody Flights flightDetailsCreate) {
		return flightService.save(flightDetailsCreate);
	}

	// method to update the flights
	@PutMapping("/flightUpdate")
	public String updateFlight(@RequestBody Flights flightDetailsCreate) {
		flightService.save(flightDetailsCreate);
		return "Updated";
	}

	// constructor
	public FlightDetailsController(FlightDetailsService flightService) {
		super();
		this.flightService = flightService;
	}

	// method to get all flights
	@GetMapping("/getAllFlights")
	public List<Flights> getFlightDetail(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		return (List<Flights>) flightService.findAllFlights(pageNumber, pageSize);
	}

	// method to flight by flight number
	@GetMapping("/getAllFlights/{flightNumber}")
	public Optional<Flights> findByFlightNumber(@PathVariable int flightNumber) {

		Optional<Flights> flights = flightService.findById(flightNumber);
		if (!flights.isPresent())
			throw new RuntimeException("Flight number is invalid " + flightNumber);

		return flights;
	}

	// method to delete flight by flight number
	@DeleteMapping("/deleteFlights/{flightNumber}")
	public String deleteByFlightNumber(@PathVariable int flightNumber) {
		Optional<Flights> flights = flightService.findById(flightNumber);
		if (!flights.isPresent())
			throw new RuntimeException("Flight number is invalid " + flightNumber);

		flightService.deleteByFlightNumber(flightNumber);
		return "Deleted flight number" + flightNumber;

	}

	// method to delete all flights
	@DeleteMapping("/flightsDelete")
	public String deleteAllFlights(Flights fareDetailsCreate) {
		flightService.deleteAll();
		return "Deleted All flight";
	}

	// method to search flights
	@GetMapping("/searchFlight")
	public ResponseEntity<List<FlightsDTO>> findFlights(@RequestParam("source") String source,
			@RequestParam("destination") String destination, @RequestParam(required = false) LocalDate departureDate,
			@RequestParam("classType") String classType, @RequestParam(required = false) String roundTrip,
			@RequestParam(required = false) LocalDate returnDate, @RequestParam(required = false) String sort,
			@RequestParam(required = false) String sortingType, @RequestParam(required = false) String departure,
			@RequestParam(required = false) String departureType,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize)
			throws IOException {
		
		List<Flights> oneWayTripFlights = flightService.searchOfFlights(source, destination, departureDate, classType,
				roundTrip, returnDate, sort, sortingType, departure, departureType, pageNumber, pageSize);

		ModelMapper model = new ModelMapper();

		List<FlightsDTO> flightsDto = new ArrayList<>();

		oneWayTripFlights.forEach(flight -> {
			List<FareDTO> fareDtoList = new ArrayList<>();
			flight.getFareDetails().forEach(fare -> fareDtoList.add(model.map(fare, FareDTO.class)));
			FlightsDTO dto = model.map(flight, FlightsDTO.class);
			dto.setFares(fareDtoList);
			flightsDto.add(dto);
		});

		model.map(oneWayTripFlights, FlightsDTO.class);
		return new ResponseEntity<>(flightsDto, HttpStatus.OK);

	}

	@GetMapping("/search")
	public String search(@RequestParam String source, @RequestParam String destination, @RequestParam String classType,
			@RequestParam(value = "departureDate") LocalDate departureDate,
			@RequestParam(value = "arrivalDate") LocalDate arrivalDate, @RequestParam int numberOfTravellers) {
			return source + " " + destination + " " + classType + " " + departureDate + " " + arrivalDate + " "
				+ numberOfTravellers;

	}

}
