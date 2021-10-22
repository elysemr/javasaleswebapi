package com.acme.sales.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //"cors" allows us to talk to back end with a front end system not in same domain
@RestController
@RequestMapping("/api/customers") //base url will point to this ctrlr
public class CustomersController {
	
	@Autowired //creates an instance of our customer repository automatically so we don't have to do it
	private CustomerRepository custRepo; //composition
	
	@GetMapping
	public Iterable<Customer> GetAll() {
		return custRepo.findAll();
	}
	/*
	@GetMapping("{id}")
	public Iterable<Customer> GetById() {
		return custRepo.getById();
	}
	
	@PutMapping("{/api/customers/}")
	
	@PostMapping("{/api/customers/}")
	
	@DeleteMapping("{/api/customers/}") */
	
	

}
