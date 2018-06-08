package com.skip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skip.entity.Product;

public interface Products extends JpaRepository<Product, Integer> {
	//@Query("SELECT p FROM Product p WHERE p.name like \'?1%\'")
	//List<Product> searchProductText(String searchText);
	List<Product> findByNameIgnoreCaseContaining(String name);
}
