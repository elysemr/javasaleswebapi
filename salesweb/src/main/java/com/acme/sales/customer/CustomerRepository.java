package com.acme.sales.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> { //need to use integer class not just int

	
	Customer findByCode(String code); //read by code in customer, don't have to write body at all, just method
										//now can call method in controller
	
	
}
