package com.aliyaa.assignment.mmt.flightDetails.entity;

import java.time.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "flight_details")
public class Flights {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightNumber", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<FareDetails> fareDetails;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_number", nullable = true)
	private int flightNumber;

	@Column(name = "airlines", nullable = false, length = 20)
	private String airlines;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "departure_date", nullable = false)
	private LocalDate departureDate;

	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name = "departure_time", nullable = false)
	private LocalTime departureTime;

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	@Column(name = "duration", nullable = false)
	private long duration;

	public long getDuration() {

		return duration;
	}

	@PrePersist
	@PreUpdate
	public void setDuration() {

		LocalDateTime departure = LocalDateTime.of(departureDate, departureTime);

		LocalDateTime arrival = LocalDateTime.of(arrivalDate, arrivalTime);
		duration = Duration.between(departure, arrival).toMinutes();

		this.duration = duration;

	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name = "arrival_time", nullable = false)
	private LocalTime arrivalTime;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "arrival_Date", nullable = false)
	private LocalDate arrivalDate;

	public List<FareDetails> getFareDetails() {
		return fareDetails;
	}

	public void setFareDetails(List<FareDetails> fareDetails) {
		this.fareDetails = fareDetails;
	}

	public Flights(List<FareDetails> fareDetails, String airlines, LocalDate departureDate, LocalTime departureTime,
			long duration, LocalTime arrivalTime, LocalDate arrivalDate, String source, String destination) {
		super();
		this.fareDetails = fareDetails;
		this.airlines = airlines;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.arrivalDate = arrivalDate;
		this.source = source;
		this.destination = destination;
		this.duration = getDuration();
	}

	@Override
	public String toString() {
		return "FlightDetailsCreate [fareDetails=" + fareDetails + ", flightNumber=" + flightNumber + ", airlines="
				+ airlines + ", departureDate=" + departureDate + ", departureTime=" + departureTime + ", duration="
				+ duration + ", arrivalTime=" + arrivalTime + ", arrivalDate=" + arrivalDate + ", source=" + source
				+ ", destination=" + destination + "]";
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Flights() {
		super();
	}

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Column(name = "source", nullable = false, length = 5)
	private String source;

	@Column(name = "destination", nullable = false, length = 5)
	private String destination;

}
