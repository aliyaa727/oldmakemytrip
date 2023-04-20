package com.aliyaa.assignment.mmt.flightDetails.service;

import java.util.List;
import java.util.Optional;

import com.aliyaa.assignment.mmt.flightDetails.entity.UserDetails;

public interface UserDetailsService {

	List<UserDetails> findAll(Integer pageSize,Integer pageNumber);
	
	UserDetails findByEmail(String email);
	
	UserDetails save(UserDetails userDetails);
	
	Optional<UserDetails> findByUserId(int UserId);
	
	void deleteAll();
	
	String deleteByUserId(int UserId);
	
	boolean existsByEmail(String email);
}
