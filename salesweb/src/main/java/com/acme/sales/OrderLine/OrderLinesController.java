package com.acme.sales.OrderLine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/orderlines")
public class OrderLinesController {
	
	@Autowired
	private OrderLineRepository olRepo;
	
	@GetMapping
	public Iterable<OrderLine> GetAll() {
		return olRepo.findAll();
	}

}
