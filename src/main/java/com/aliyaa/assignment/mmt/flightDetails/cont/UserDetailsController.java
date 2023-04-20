package com.aliyaa.assignment.mmt.flightDetails.cont;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyaa.assignment.mmt.flightDetails.entity.UserDetails;
import com.aliyaa.assignment.mmt.flightDetails.service.UserDetailsService;

@RestController
public class UserDetailsController {

	@Autowired
	private UserDetailsService userService;

	public UserDetailsController(UserDetailsService userService) {
		super();
		this.userService = userService;
	}
	
	
	@PostMapping("/userDetails")
	public UserDetails saveDetails(@RequestBody UserDetails userDetails)
	{
		return userService.save(userDetails);
	}
	
	@GetMapping("/getAllUsers")
	public List<UserDetails> getAllUsers(@RequestParam(value="pageNumber",defaultValue="1", required=false) 
	Integer pageNumber,
	@RequestParam(value="pageSize",defaultValue="10", required=false) Integer pageSize)
	{
		return userService.findAll(pageNumber,pageSize );
	}
	@GetMapping("/getAllUsers/{email}")
	public UserDetails getAllByEmail(@PathVariable String email)
	{
		return userService.findByEmail(email);
	}

	@GetMapping("/getAllUsers/{bookingId}")
	public Optional<UserDetails> getAllByBookingId(@PathVariable int UserId)
	{
		return userService.findByUserId(UserId);
	}
	@DeleteMapping("/deleteUser")
	 public String deleteAll()
	 {
		userService.deleteAll();   
		return "Deleted All users";
	 }
	@DeleteMapping("/deleteUser/{bookingId}")
	 public String delete(@PathVariable int UserId)
	 {
		userService.deleteByUserId(UserId) ;  
		return "Deleted booking";
	 }
	
	
}
