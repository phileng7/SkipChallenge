package com.skip.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.skip.entity.Order;
import com.skip.entity.Product;
import com.skip.repository.Orders;

@Controller
@RequestMapping("api/v1")
public class OrderController {
	@Autowired
	private Orders orders;
	
	//Get all orders
	@GetMapping("Order")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> list = orders.findAll();
		return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
	}
	
	@GetMapping("Order/{orderId}")
	public ResponseEntity<?> getProductById(@PathVariable("orderId") Integer id) {
		Optional<Order> ord = orders.findById(id);
		if (ord.isPresent())
			return new ResponseEntity<Order>(ord.get(), HttpStatus.OK);
		return new ResponseEntity<String>("Order ID does not exist", HttpStatus.BAD_REQUEST);	
	}
	
	//Get all orders by customer
	@GetMapping("Order/customer")
	public ResponseEntity<List<Order>> getAllOrdersByCustomer() {
		List<Order> list = orders.orderByCustomer();
		return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
	}
	
	@PostMapping("Order")
	public ResponseEntity<Void> addArticle(@RequestBody Order order, UriComponentsBuilder builder) {
		Object ret = orders.save(order);
		if (ret!=null) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
    	    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
	}
}
