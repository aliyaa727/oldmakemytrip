package com.aliyaa.assignment.mmt.flightDetails.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="booking_details")
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="booking_id")
	private int bookingId;
	
	@Column(name="booking_time")
	private LocalDateTime bookingTime;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id",nullable=false)
	@JsonBackReference(value="user-details")
	private UserDetails userDetails;
	
	@ManyToOne
	@JoinColumn(name="fare_id", nullable=false)
	@JsonBackReference(value="booking-details")
	private FareDetails fareDetails;

	public BookingDetails() {
		super();
	}

	public BookingDetails(int bookingId, LocalDateTime bookingTime, FareDetails fareDetails) {
		super();
		this.bookingId = bookingId;
		this.bookingTime = getBookingTime();
		this.fareDetails = fareDetails;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", bookingTime=" + bookingTime + ", userDetails="
				+ userDetails + ", fareDetails=" + fareDetails + "]";
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
		
	}
	@PrePersist
	@PreUpdate
	public void setBookingTime() {
		bookingTime=LocalDateTime.now();
		this.bookingTime =bookingTime;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public FareDetails getFareDetails() {
		return fareDetails;
	}

	public void setFareDetails(FareDetails fareDetails) {
		this.fareDetails = fareDetails;
	}

}
