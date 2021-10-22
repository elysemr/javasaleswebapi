package com.acme.sales.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.acme.sales.customer.Customer;

@Entity(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String description;
	@Column(columnDefinition="decimal(10,2) NOT NULL DEFAULT 0.0")
	private double total;
	
	//this is the way to create a FK
	@ManyToOne(optional=false) //optional=false means required
	@JoinColumn(name="customerId") //put column in sql table
	private Customer customer; //create a property/instance of customer
	//now you need a customer instance in order to create an order, not just the customer ID
	
	
	public Order () {}	
	
	
	
	public int getId() {
		return id;
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
