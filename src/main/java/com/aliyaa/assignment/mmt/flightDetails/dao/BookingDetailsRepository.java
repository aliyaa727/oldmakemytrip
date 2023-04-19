package com.aliyaa.assignment.mmt.flightDetails.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;
@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {

	//Page<BookingDetails> findByFareFlightNumberFlightId(Pageable page, int flightNumber);

	//Page<BookingDetails> findByEmail(Pageable page, String email);

}
