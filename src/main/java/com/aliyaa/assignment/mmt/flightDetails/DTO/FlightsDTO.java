package com.aliyaa.assignment.mmt.flightDetails.DTO;

import java.time.LocalTime;
import java.util.List;

public class FlightsDTO
{
	String airlines;
	LocalTime departureTime;
	LocalTime arrivalTime;
	long duration;
	List<FareDTO> fares;
	
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public List<FareDTO> getFares() {
		return fares;
	}
	public void setFares(List<FareDTO> fares) {
		this.fares = fares;
	}
	
	

}
