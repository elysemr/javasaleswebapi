package com.acme.sales.customer;

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

@CrossOrigin //"cors" allows us to talk to back end with a front end system not in same domain
@RestController
@RequestMapping("/api/customers") //base url will point to this ctrlr
public class CustomersController {
	
	@Autowired //creates an instance of our customer repository automatically so we don't have to do it
	private CustomerRepository custRepo; //composition
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> GetAll() {
		var customers = custRepo.findAll(); //will give us collection
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
			//^^passing back collection of customers and also error code so we can control what error code says
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Customer> GetById(@PathVariable int id) { //path variable: coming in from URL
		var customer = custRepo.findById(id); //not an instance of customer, instance of optional that passes in customer
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //don't want to return null so response entity 
																//points to real response with message
		}
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK); //.get is how you have to return
	}
	
	@PostMapping
	public ResponseEntity<Customer> Insert(@RequestBody Customer customer) { //request body: path variable 
														//but being passed in in body of request b/c it's a whole instance
		if(customer == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //not required for capstone, but good practice IRL
		}
		customer.setId(0); //makes sure Id is set to 0
		var newCustomer = custRepo.save(customer); //take entity which is customer instance
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED); //save returns customer so trap that in var and return that bc it has the correct id
				//POST must return something, can't return no content
	}
	
	@PutMapping("{id}") //need URL because need to put in ID when updating
	public ResponseEntity Update(@PathVariable int id, @RequestBody Customer customer) /*throws Exception */ //path var and data
	{
		if(customer.getId() != id) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// throw new Exception("Ids do not match");
		}
		//could trap read by code to make sure no 2 customers have the same code
		/* var updatedCustomer = */ 
		custRepo.save(customer); //can choose not to store anything here bc not returning anything
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
	
	@DeleteMapping("{id}") 
	public ResponseEntity Delete(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		//customer.get();
	}
	
	

}
