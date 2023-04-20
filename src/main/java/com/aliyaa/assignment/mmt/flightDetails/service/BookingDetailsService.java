package com.aliyaa.assignment.mmt.flightDetails.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.aliyaa.assignment.mmt.flightDetails.DTO.AllBookingsDTO;
import com.aliyaa.assignment.mmt.flightDetails.DTO.BookingDTO;
import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;

public interface BookingDetailsService {
	
	BookingDetails save(BookingDTO bookingDTO);
	
	Optional<BookingDetails> findByBookingId(int bookingId);
	
	void deleteAll();
	
	String deleteByBookingId(int bookingId);

	List<AllBookingsDTO> findAllBookings(Integer pageNumber,Integer pageSize, int flightNumber, String email, String sort, String sortingType) throws IOException;


}
