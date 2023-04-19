package com.aliyaa.assignment.mmt.flightDetails.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity

@Table(name="user_details")
public class UserDetails {
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy="userDetails", fetch = FetchType.EAGER)
	@JsonManagedReference(value="user-details")
	private List<BookingDetails> bookingDetails;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	  
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phone_number", length=10)
	private long phoneNumber;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="email")
	private String email;

	
	
	public UserDetails() {
		super();
	}


	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", email=" + email + "]";
	}


	public UserDetails(int userId, String firstName, String lastName, long phoneNumber, String gender, String email) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.email = email;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
