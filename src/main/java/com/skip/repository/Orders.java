package com.skip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skip.entity.Order;

public interface Orders extends JpaRepository<Order, Integer> {
	@Query("SELECT o FROM Order o GROUP BY o.customer")
	List<Order> orderByCustomer();
}
