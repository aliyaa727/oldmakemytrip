package com.aliyaa.assignment.mmt.flightDetails.service;

import java.util.Optional;

import com.aliyaa.assignment.mmt.flightDetails.DTO.BookingDTO;
import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;

public interface BookingDetailsService {
	
	BookingDetails save(BookingDTO bookingDTO);
	
//	Optional<UserDetails> findByFlightNumber(int flightNumber);
	
	
	Optional<BookingDetails> findByBookingId(int bookingId);
	
	void deleteAll();
	
	String deleteByBookingId(int bookingId);

//	List<AllBookingsDTO> findAllBookings(Pageable page, int flightNumber, String email);
	 

}
