package com.acme.sales.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.sales.customer.Customer;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	
	@Autowired
	private OrderRepository ordRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Order>> GetAll() {
		var orders = ordRepo.findAll();
		return new ResponseEntity<Iterable<Order>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> GetById(@PathVariable int id) {
		var order = ordRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Order> Insert(@RequestBody Order order) {
		if(order == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		order.setId(0);
		var newOrder = ordRepo.save(order);
		return new ResponseEntity<Order>(newOrder, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody Order order) {
		if(order.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ordRepo.save(order);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) {
		var order = ordRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ordRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("customer/{customerId}") //need to differentiate between getbyId
	public ResponseEntity<Iterable<Order>> GetOrderNotCustomer(@PathVariable int customerId) { //going to bring back a collection of 0+
																							//method name can be whatever you want
		var orders = ordRepo.findByCustomerIdNot(customerId);
		return new ResponseEntity<Iterable<Order>>(orders, HttpStatus.OK);
		//don't need "if doesn't exist" scenario because it's exclusionary so if user doesn't exist
		//then it will just return all the orders and not exclude any
	}
	
	//like set request to review, set order back to 0
	@PutMapping("review/{id}")
	public ResponseEntity SetOrderToZero(@PathVariable int id, @RequestBody Order order) {
		var newTotal = order.getTotal() <= 100 ? 0 : 1000;
		order.setTotal(newTotal);
		return Update(id, order);
	}
	
	//like set to approved
	@PutMapping("approve/{id}") 
	public ResponseEntity SetOrderTo5000(@PathVariable int id, @RequestBody Order order) {
		order.setTotal(5000);
		return Update(id, order);
	}
	
	//like set to reject
	@PutMapping("reject/{id}")
	public ResponseEntity SetOrderToNetative5000(@PathVariable int id, @RequestBody Order order) {
		order.setTotal(-5000);
		return Update(id, order);
	}
	
	

}
