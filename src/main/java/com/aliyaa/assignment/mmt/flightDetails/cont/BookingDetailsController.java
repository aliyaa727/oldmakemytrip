package com.aliyaa.assignment.mmt.flightDetails.cont;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyaa.assignment.mmt.flightDetails.DTO.AllBookingsDTO;
import com.aliyaa.assignment.mmt.flightDetails.DTO.BookingDTO;
import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;
import com.aliyaa.assignment.mmt.flightDetails.service.BookingDetailsService;

@RestController
public class BookingDetailsController {
	
	@Autowired
	private BookingDetailsService bookService;
//constructor
	public BookingDetailsController(BookingDetailsService bookService) {
		super();
		this.bookService = bookService;
	}
	@GetMapping("/bookings")
	public List<AllBookingsDTO> getAllBookings(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "0") int flightNumber,
     		@RequestParam(required = false) String email, @RequestParam(required=false,defaultValue="false") String sort,
     		@RequestParam(required=false,defaultValue="bookingtime") String sortingType ) throws IOException {
			List<AllBookingsDTO> bookinglist = bookService.findAllBookings(pageNumber,pageSize, flightNumber, email,sort,sortingType);
			return bookinglist;
			}
//method to save all the bookings
	@PostMapping("/saveBookings")
	public String saveBookings(@RequestBody BookingDTO bookingDTO)
	{
		bookService.save(bookingDTO);
		return "Saved";
	}
//method to delete by booking id
	@DeleteMapping("/deleteBookings/{bookingId}")
	public String deleteByBookingId(@PathVariable int bookingId)
	{
		bookService.deleteByBookingId(bookingId);
		return "Deleted the booking id "+bookingId;
	}
//method to delete all bookings
	@DeleteMapping("/deleteBookings")
	public String deleteAll()
	{
		bookService.deleteAll();
		return "Deleted all the booking ";
	}
//method to get by booking id
   @GetMapping("/Bookings/{bookingId}")
   public Optional<BookingDetails> find(@PathVariable int bookingId)
   {
	   return bookService.findByBookingId(bookingId);
   }
}
