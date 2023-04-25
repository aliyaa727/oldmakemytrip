package com.aliyaa.assignment.mmt.flightDetails.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aliyaa.assignment.mmt.flightDetails.DTO.AllBookingsDTO;
import com.aliyaa.assignment.mmt.flightDetails.DTO.BookingDTO;
import com.aliyaa.assignment.mmt.flightDetails.dao.BookingDetailsRepository;
import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;
import com.aliyaa.assignment.mmt.flightDetails.entity.FareDetails;
import com.aliyaa.assignment.mmt.flightDetails.entity.UserDetails;
import com.aliyaa.assignment.mmt.flightDetails.exceptions.Validations;

@Service
public class BookingDetailsImplement implements BookingDetailsService {

	@Autowired
	private BookingDetailsRepository bookSerRepo;
	@Autowired
	private UserDetailsImplement userService;
	@Autowired
	private FareDetailsImplement fareService;

	@Override
	public List<AllBookingsDTO> findAllBookings(Integer pageNumber, Integer pageSize, int flightNumber, String email,
			String sort, String sortingType) throws IOException {

		// TODO Auto-generated method stub
		Validations validations = new Validations();
		validations.inputForBookingDetails(sort.toLowerCase(), sortingType.toLowerCase());
		if (pageNumber < 0) {
			throw new RuntimeException("Page Number cannot be less than zero");
		}
		if (pageSize <= 0) {
			throw new RuntimeException("Page Size cannot be less than or equal to zero");
		}
		Pageable page = null;
		if (sort.toLowerCase().equals("true")) {
			if (sortingType.toLowerCase().equals("bookingtime")) {
				page = PageRequest.of(pageNumber, pageSize, Sort.by("bookingTime"));
			} else if (sortingType.toLowerCase().equals("fares")) {
				page = PageRequest.of(pageNumber, pageSize, Sort.by("fare"));
			}
		} else {
			page = PageRequest.of(pageNumber, pageSize);
		}

		Page<BookingDetails> booking;
		if (flightNumber != 0) {
			booking = bookSerRepo.findByFareDetailsFlightNumber(page, flightNumber);
		} else {
			if (email == null) {
				booking = bookSerRepo.findAll(page);
			} else {
				booking = bookSerRepo.findByUserDetailsEmail(page, email);
			}
		}
		List<BookingDetails> bookings = booking.getContent();
		if (bookings.isEmpty()) {
			throw new RuntimeException("Bookings Count :0");
		}
	  return bookings.stream()
				.map(bookingDetails -> new AllBookingsDTO(
						bookingDetails.getFareDetails().getFlightNumber().getAirlines(),
						bookingDetails.getFareDetails().getFlightNumber().getDepartureDate(),
						bookingDetails.getFareDetails().getFlightNumber().getArrivalDate(),
						bookingDetails.getFareDetails().getFlightNumber().getDepartureTime(),
						bookingDetails.getFareDetails().getFlightNumber().getArrivalTime(),
						bookingDetails.getFareDetails().getFlightNumber().getSource(),
						bookingDetails.getFareDetails().getFlightNumber().getDestination(),
						bookingDetails.getFareDetails().getFlightNumber().getDuration(),
						bookingDetails.getFareDetails().getClassType(), bookingDetails.getFareDetails().getFare(),
						bookingDetails.getUserDetails()))
				.collect(Collectors.toList());
	}

	public BookingDetailsImplement(BookingDetailsRepository bookSerRepo, UserDetailsImplement userService,
			FareDetailsImplement fareService) {
		super();
		this.bookSerRepo = bookSerRepo;
		this.userService = userService;
		this.fareService = fareService;
	}


	@Override
	public BookingDetails save(BookingDTO bookingDetails) {

		BookingDetails booking = new BookingDetails();
		UserDetails userDetails = new UserDetails();
		userDetails.setFirstName(bookingDetails.firstName());
		userDetails.setLastName(bookingDetails.lastName());
		userDetails.setEmail(bookingDetails.email());
		userDetails.setPhoneNumber(bookingDetails.phoneNumber());
		userDetails.setGender(bookingDetails.gender());

		if (userService.findByEmail(bookingDetails.email()) != null) {
			UserDetails existingUser = userService.findByEmail(bookingDetails.email());
			booking.setUserDetails(existingUser);
		} else {
			UserDetails user = userService.save(userDetails);
			booking.setUserDetails(user);

		}

		FareDetails fare = fareService.findFareById(bookingDetails.fareDetails());
		if (fare != null) {
			booking.setFareDetails(fare);
		}

		bookSerRepo.save(booking);

		return booking;
	}

	@Override
	public String deleteByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		bookSerRepo.deleteById(bookingId);
		return "Deleted";
	}

	@Override
	public Optional<BookingDetails> findByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		return bookSerRepo.findById(bookingId);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		bookSerRepo.deleteAll();

	}

}
