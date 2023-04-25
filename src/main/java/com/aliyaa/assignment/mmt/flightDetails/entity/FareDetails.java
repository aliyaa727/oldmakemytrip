package com.aliyaa.assignment.mmt.flightDetails.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "fare_details")
public class FareDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "flight_number", nullable = false)
	@JsonBackReference
	private Flights flightNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fareDetails", fetch = FetchType.EAGER)
	@JsonManagedReference(value="booking-details")
	private List<BookingDetails> bookingDetails;

	@Override
	public String toString() {
		return "FareDetails [id=" + id + ", flightNumber=" + flightNumber + ", bookingDetails=" + bookingDetails
				+ ", classType=" + classType + ", fare=" + fare + "]";
	}

	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public FareDetails(int id, Flights flightNumber, List<BookingDetails> bookingDetails, String classType, int fare) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.bookingDetails = bookingDetails;
		this.classType = classType;
		this.fare = fare;
	}

	public FareDetails() {
		super();
	}

	public Flights getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Flights flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}
	 @NotNull
	@Column(name = "Class_type")
	private String classType;
	 @NotNull
	@Column(name = "fare")
	private int fare;


}
