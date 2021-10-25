package com.acme.sales.OrderLine;

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

@CrossOrigin
@RestController
@RequestMapping("/api/orderlines")
public class OrderLinesController {
	
	@Autowired
	private OrderLineRepository olRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<OrderLine>> GetAll() {
		var orderLines = olRepo.findAll();
		return new ResponseEntity<Iterable<OrderLine>>(orderLines, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OrderLine> GetById(@PathVariable int id) {
		var orderLine = olRepo.findById(id);
		if(orderLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrderLine>(orderLine.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrderLine> Insert(@RequestBody OrderLine orderLine) {
		if(orderLine == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		orderLine.setId(0);
		var newOrderLine = olRepo.save(orderLine);
		return new ResponseEntity<OrderLine>(newOrderLine, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody OrderLine orderLine) {
		if(orderLine.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		olRepo.save(orderLine);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity Delete (@PathVariable int id) {
		var orderLine = olRepo.findById(id);
		if(orderLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		olRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
