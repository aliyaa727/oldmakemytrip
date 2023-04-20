package com.aliyaa.assignment.mmt.flightDetails.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;
@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

	Page<BookingDetails> findByFareDetailsFlightNumber(Pageable page, int flightNumber);

	
	Page<BookingDetails> findByUserDetailsEmail(Pageable page, String email);

}
