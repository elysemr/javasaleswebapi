package com.acme.sales.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> { //need to use integer class not just int

	Optional<Customer> findByCodeAndName(String code, String name); //when you want to retrieve something that matches, 
																	//pass in names of columns want to search by
	
	
	
	//Customer findByCode(String code); //read by code in customer, don't have to write body at all, just method
										//now can call method in controller

	
	
}
