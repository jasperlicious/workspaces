package com.accenture.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accenture.hotel.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
	@Query(value = "Select COUNT(users_role) From users where users_role ='STAFF'", nativeQuery = true)
	public int countStaff();

}
