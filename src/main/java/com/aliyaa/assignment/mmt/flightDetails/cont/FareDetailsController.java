package com.aliyaa.assignment.mmt.flightDetails.cont;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aliyaa.assignment.mmt.flightDetails.entity.FareDetails;
import com.aliyaa.assignment.mmt.flightDetails.service.FareDetailsService;

@RestController
public class FareDetailsController {
	
	@Autowired(required = false)
	private FareDetailsService fareService;
	
	public FareDetailsController(FareDetailsService fareService) {
	super();
	this.fareService = fareService;
	}
	@GetMapping("/fares")
	public List<FareDetails> getAllFares() {
	return fareService.findAllFares();
	}
	@PostMapping("/fares/{flightNumber}")
	public FareDetails addFare(@RequestBody FareDetails fareDetails) {
	FareDetails faredetails = fareService.save(fareDetails);
	return faredetails;
	}
	@PutMapping("/faresUpdate/{flightNumber}")
	public FareDetails updateFare(@RequestBody FareDetails fareDetails) {
	FareDetails fare_details = fareService.save(fareDetails);
	return fare_details;
	 }
	@DeleteMapping("/deleteFares/{flightNumber}")
	public String deleteFare(@PathVariable int flightNumber) {
	fareService.deleteByFlightNumber(flightNumber);
	return "Deleted fare for flight number" + flightNumber;
	}
	@DeleteMapping("/deleteAllFares")
	public String deleteAllFares(FareDetails fareDetails) {
	fareService.deleteAll();
	return "Deleted all flight fares";
	}
	
}
