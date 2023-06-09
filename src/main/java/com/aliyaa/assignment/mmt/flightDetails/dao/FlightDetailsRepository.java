package com.aliyaa.assignment.mmt.flightDetails.dao;




import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.aliyaa.assignment.mmt.flightDetails.entity.Flights;

import org.springframework.stereotype.Repository;

@Repository
public interface FlightDetailsRepository extends JpaRepository<Flights, Integer> {
	
	
		public	List<Flights> findBySourceAndDestinationAndDepartureDateAndFareDetailsClassType(@Param("source") String source, 
				@Param("destination") String destination ,
			@Param("departureDate") LocalDate departureDate, @Param("classType") String classType);

	


		public List<Flights> findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByDuration(String source,
				String destination, LocalDate departureDate, String classType);



		public List<Flights> findBySourceAndDestinationAndDepartureDateAndFareDetailsClassTypeOrderByFareDetailsFare(
				String source, String destination, LocalDate departureDate, String classType);






}
