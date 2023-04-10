   package com.aliyaa.assignment.mmt.flightDetails.entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
	@Entity
	@Table(name="fare_details")
	public class FareDetails {
	
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="flight_number",nullable=false)
	@JsonBackReference
	private Flights flightNumber;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	
		
		public FareDetails(int id,Flights flightNumber, String classType, int fare) {
			super();
			this.id=id;
			this.flightNumber = flightNumber;
			this.classType = classType;
			this.fare = fare;
		}

		@Override
		public String toString() {
			return "FareDetails [id=" + id + ", flightNumber=" + flightNumber + ", classType=" + classType + ", fare="
					+ fare + "]";
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

		@Column(name="Class_type", nullable=false)
		private String classType;
		
		@Column(name="fare", nullable=false)
		private int fare;
		
		

	}


