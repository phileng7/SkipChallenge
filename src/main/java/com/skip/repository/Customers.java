package com.skip.repository;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skip.entity.Customer;

public interface Customers extends JpaRepository<Customer, Integer> {
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = ?1 AND c.password = ?2")
	boolean existsByEmailPasswd(String email, String password);
}
