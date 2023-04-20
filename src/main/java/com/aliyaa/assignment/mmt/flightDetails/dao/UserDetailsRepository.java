package com.aliyaa.assignment.mmt.flightDetails.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aliyaa.assignment.mmt.flightDetails.entity.UserDetails;
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	public Optional<UserDetails> findByEmail(@Param ("email") String email);
	public boolean existsByEmail(String email);
}
