package com.aliyaa.assignment.mmt.flightDetails.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyaa.assignment.mmt.flightDetails.DTO.BookingDTO;
import com.aliyaa.assignment.mmt.flightDetails.dao.BookingDetailsRepository;
import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;
import com.aliyaa.assignment.mmt.flightDetails.entity.FareDetails;
import com.aliyaa.assignment.mmt.flightDetails.entity.UserDetails;
@Service
public class BookingDetailsImplement implements BookingDetailsService {

	@Autowired
	private BookingDetailsRepository bookSerRepo;
	@Autowired
	private UserDetailsImplement userService;
	@Autowired
	private FareDetailsImplement fareService;
	
	//@Override
	//public List<AllBookingsDTO> findAllBookings(Pageable page,int flightNumber,String email) {
		// TODO Auto-generated method stub
		
//		Page<BookingDetails> booking_details;
//		if (flightNumber != 0) {
//		booking_details = bookSerRepo.findByFareFlightNumberFlightId(page, flightNumber);
//		}
//		else {
//		if (email == null) {
//		booking_details = bookSerRepo.findAll(page);
//		} else {
//		booking_details = bookSerRepo.findByEmail(page, email);
//		}
//		}
//		  List<BookingDetails> booking = booking_details.getContent();
//		if (booking.isEmpty()) {
//		throw new RuntimeException("Bookings Count :0");
//		}
//		return booking.stream()
//				 .map(bookingDetails -> new AllBookingsDTO(bookingDetails.getFareDetails().getFlightNumber().getAirlines(),
//				 bookingDetails.getFareDetails().getFlightNumber().getDepartureDate(),
//				 bookingDetails.getFareDetails().getFlightNumber().getArrivalDate(),
//				 bookingDetails.getFareDetails().getFlightNumber().getDepartureTime(),
//				 bookingDetails.getFareDetails().getFlightNumber().getArrivalTime(),
//				 bookingDetails.getFareDetails().getFlightNumber().getSource(),
//				 bookingDetails.getFareDetails().getFlightNumber().getDestination(),
//				 bookingDetails.getFareDetails().getFlightNumber().getDuration(),
//				 bookingDetails.getFareDetails().getClassType(), bookingDetails.getFareDetails().getFare(),
//				 bookingDetails.getUserDetails()))
//				 .collect(Collectors.toList());
	//	return null;
	//	}
	

	

	public BookingDetailsImplement(BookingDetailsRepository bookSerRepo, UserDetailsImplement userService,
			FareDetailsImplement fareService) {
		super();
		this.bookSerRepo = bookSerRepo;
		this.userService = userService;
		this.fareService = fareService;
	}



	@Override
	public BookingDetails save(BookingDTO bookingDetails) {
	/*// TODO Auto-generated method stub
		UserDetails userDetails = bookingDetails.getUserDetails();
		UserDetails savedUser = userService.save(userDetails);
		bookingDetails.setUserDetails(savedUser);
		FareDetails fareDetails = bookingDetails.getFareDetails();
		FareDetails savedFare = fareService.save(fareDetails);
		bookingDetails.setFareDetails(savedFare);
		if (savedUser == null) {
		throw new RuntimeException("Saved User is NULL");
		}
		if (savedFare == null) {
		throw new RuntimeException("Saved Fare is NULL");
		}
		return bookSerRepo.save(bookingDetails);*/
		BookingDetails booking=new BookingDetails();
		UserDetails userDetails=new UserDetails();
		userDetails.setFirstName(bookingDetails.firstName());
		userDetails.setLastName(bookingDetails.lastName());
		userDetails.setEmail(bookingDetails.email());
		userDetails.setPhoneNumber(bookingDetails.phoneNumber());
		userDetails.setGender(bookingDetails.gender());
//	if(!(userService.existsByEmail(bookingDetails.email())))
//	{
//		userService.save(userDetails);
//	}
//	else {
//		booking.setUserDetails(userDetails);
//	
//	}
//	
//	FareDetails fareDetail=fareService.findFareById(bookingDetails.fareDetails());
//	
//	if(fareDetail==null)
//	{
//		throw new RuntimeException("No flight found with the given id");
//	}
//	booking.setFareDetails(fareDetail);
//	bookSerRepo.save(booking);
		
		if(userService.findByEmail(bookingDetails.email())!=null){
		    UserDetails existingUser=userService.findByEmail(bookingDetails.email());
		    booking.setUserDetails(existingUser);
		}
		else
		{
		    UserDetails user=userService.save(userDetails);
		    booking.setUserDetails(user);

		}

		FareDetails fare=fareService.findFareById(bookingDetails.fareDetails());
		if (fare!= null){
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
