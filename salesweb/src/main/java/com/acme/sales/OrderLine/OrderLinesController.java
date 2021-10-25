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

import com.acme.sales.order.OrderRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/orderlines")
public class OrderLinesController {
	
	@Autowired
	private OrderLineRepository olRepo;
	@Autowired //creating var and system inject instance of repo 
	private OrderRepository ordRepo; //need this for recalculate method bc calling instance of ordrepo
	
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
	public ResponseEntity<OrderLine> Insert(@RequestBody OrderLine orderLine) throws Exception {
		if(orderLine == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		orderLine.setId(0);
		var newOrderLine = olRepo.save(orderLine);
		RecalculateOrder(orderLine.getOrder().getId());
		return new ResponseEntity<OrderLine>(newOrderLine, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody OrderLine orderLine) throws Exception {
		if(orderLine.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		olRepo.save(orderLine);
		RecalculateOrder(orderLine.getOrder().getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity Delete (@PathVariable int id) throws Exception {
		var orderLine = olRepo.findById(id);
		if(orderLine.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		olRepo.deleteById(id);
		RecalculateOrder(orderLine.get().getOrder().getId()); //orderline is optional, get gets actual orderline, 
																//then gets instance, then get id on instance
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	private void RecalculateOrder(int orderId) throws Exception {
		var optOrder = ordRepo.findById(orderId);
		if(optOrder.isEmpty()) { //did we find the order (bc it's optional)?
			throw new Exception("Order id is invalid.");
		}
		//assuming we found it
		var order = optOrder.get(); //now order is an actual order
		var orderLines = olRepo.findOrderLineByOrderId(orderId);
		var total = 0;
		for(var ordLine : orderLines) {
			total += ordLine.getQuantity() * ordLine.getPrice();
			}
		order.setTotal(total);
		ordRepo.save(order);
	}

}
