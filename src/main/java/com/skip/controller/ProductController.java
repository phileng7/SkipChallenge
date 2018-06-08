package com.skip.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.skip.entity.Customer;
import com.skip.entity.Product;
import com.skip.repository.Products;

@Controller
@RequestMapping("api/v1")
public class ProductController {
	@Autowired
	private Products products;

	@GetMapping("Product")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = products.findAll();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@GetMapping("Product/search/{searchText}")
	public ResponseEntity<List<Product>> getProductById(@PathVariable("searchText") String searchText) {
		List<Product> list = products.findByNameIgnoreCaseContaining(searchText);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@GetMapping("Product/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") int id) {
		Optional<Product> prod = products.findById(id);
		if (prod.isPresent())
			return new ResponseEntity<Product>(prod.get(), HttpStatus.OK);
		return new ResponseEntity<String>("Product ID does not exist", HttpStatus.BAD_REQUEST);		
	}	
	
	@PostMapping("Product")
	public ResponseEntity<Void> addProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        Object flag = products.saveAndFlush(product);
		if (flag!=null) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
    	    return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
	}
	
	@DeleteMapping("Product/{productId}")
	public ResponseEntity<?> delProduct(@PathVariable("productId") int productId) {
		try {
			products.deleteById(productId);
		} catch (EmptyResultDataAccessException ex) {
			return new ResponseEntity<String>("Product ID does not exist", HttpStatus.BAD_REQUEST);
		}
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
