package com.aliyaa.assignment.mmt.flightDetails.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record BookingDTO(
		
		 int fareDetails,
		 @NotEmpty(message="Enter the first name")
			
		String firstName,
		@NotEmpty(message="Enter the last name")
		
		String lastName,
		
		long phoneNumber,
		@NotEmpty(message="Enter the gender")
		
		String gender,
		@NotEmpty(message="Enter the email")
		@Email
		String email
		) {

}
