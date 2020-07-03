package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.JwtUser;


@Repository
public interface JwtRepository extends JpaRepository<JwtUser, Integer> {
	
	Boolean existsByUsername(String username);
    JwtUser findByUsernameIgnoreCase(String username);
	
}

