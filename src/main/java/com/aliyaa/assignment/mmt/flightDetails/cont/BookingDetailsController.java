package com.aliyaa.assignment.mmt.flightDetails.cont;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aliyaa.assignment.mmt.flightDetails.DTO.BookingDTO;
import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;
import com.aliyaa.assignment.mmt.flightDetails.service.BookingDetailsService;

@RestController
public class BookingDetailsController {
	
	@Autowired
	private BookingDetailsService bookService;

	public BookingDetailsController(BookingDetailsService bookService) {
		super();
		this.bookService = bookService;
	}
//	@GetMapping("/bookings")
//	public List<AllBookingsDTO> getAllBookings(
//			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
//			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
//			@RequestParam(required = false, defaultValue = "0") int flightNumber,
//			@RequestParam(required = false) String email) {
//			// EXCEPTIONS FOR PAGING in "GETUSERS"
//			if (pageNumber < 0) {
//			throw new RuntimeException("Page Number cannot be less than zero");
//			}
//			if (pageSize <= 0) {
//			throw new RuntimeException("Page Size cannot be less than or equal to zero");
//			}
//			Pageable page = PageRequest.of(pageNumber, pageSize);
//			List<AllBookingsDTO> bookinglist = bookService.findAllBookings (page, flightNumber, email);
//			return bookinglist;
//			}
	@PostMapping("/saveBookings")
	public String saveBookings(@RequestBody BookingDTO bookingDTO)
	{
		bookService.save(bookingDTO);
		return "Saved";
	}
	@DeleteMapping("/deleteBookings/{bookingId}")
	public String deleteByBookingId(@PathVariable int bookingId)
	{
		bookService.deleteByBookingId(bookingId);
		return "Deleted the booking id "+bookingId;
	}
	@DeleteMapping("/deleteBookings")
	public String deleteAll()
	{
		bookService.deleteAll();
		return "Deleted all the booking ";
	}
   @GetMapping("/Bookings/{bookingId}")
   public Optional<BookingDetails> find(@PathVariable int bookingId)
   {
	   return bookService.findByBookingId(bookingId);
   }
}
