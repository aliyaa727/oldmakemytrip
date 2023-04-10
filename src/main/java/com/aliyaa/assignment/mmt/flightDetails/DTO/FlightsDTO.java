package com.aliyaa.assignment.mmt.flightDetails.DTO;

import java.time.LocalTime;
import java.util.stream.IntStream;

public record FlightsDTO(
		String airlines,
		LocalTime departureTime,
		LocalTime arrivalTime,
		long duration,
		IntStream fare
		)
{

}
