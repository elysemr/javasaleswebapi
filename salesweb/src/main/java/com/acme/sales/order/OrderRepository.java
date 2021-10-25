package com.acme.sales.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByCustomerIdNot(int customerId); //list is discrete class like iterable
														//find all orders by customer ID and then exclude (not)
														//to show everything but those, FOR THE CAPSTONE!
}
