package com.aliyaa.assignment.mmt.flightDetails.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aliyaa.assignment.mmt.flightDetails.dao.UserDetailsRepository;
import com.aliyaa.assignment.mmt.flightDetails.entity.BookingDetails;
import com.aliyaa.assignment.mmt.flightDetails.entity.UserDetails;

@Service
public class UserDetailsImplement implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userSerRepo;
	
	@Override
	public List<UserDetails> findAll(Integer pageSize, Integer pageNumber) {
		// TODO Auto-generated method stub
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<UserDetails> pageUsers=userSerRepo.findAll(pageable);
		List<UserDetails> allUsers=pageUsers.getContent();
		
		return allUsers;
	}

	@Override
	public UserDetails findByEmail(String email) {
		// TODO Auto-generated method stub
		
		return userSerRepo.findByEmail(email).orElse(null);
	}

	@Override
	public UserDetails save(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return userSerRepo.save(userDetails);
	}


	@Override
	public Optional<UserDetails> findByUserId(int UserId) {
		// TODO Auto-generated method stub
		return userSerRepo.findById(UserId);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	 userSerRepo.deleteAll();
	}

	@Override
	public String deleteByUserId(int userId) {
		// TODO Auto-generated method stub
		userSerRepo.deleteById(userId);
		return "Deleted";
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userSerRepo.existsByEmail(email);
	}

	public Page<BookingDetails> findByEmail(Pageable page, String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
