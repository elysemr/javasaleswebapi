package com.maxtrain.tutorial;

import com.maxtrain.tutorial.models.Customer;
import com.maxtrain.tutorial.models.PrimeCustomer;
//can also import all models in package: import com.maxtrain.tutorial.models.*; <<makes bigger application

public class HelloWorldJava {

	public static void main(String[] args) {
		
		var primeCustomer2 = new PrimeCustomer("ABC", 1000);
		var primeCustomer = new PrimeCustomer("MAX");
		
		//first create an instance of class
		var customer = new Customer(); //import package (like using statement but for a class)
		customer.setName("Fred Inc.");
		customer.setSales(2000);
		customer.setSales(customer.getSales() + 1000);
		var message = String.format("Customer: %d | %s | %f", 
				customer.getId(),
				customer.getName(),
				customer.getSales()); //best way to create string interpolation
		
	//another way to create string interpolation: System.out.println("Customer: "+ id + " | "+ name + " | " + sales);
		
		System.out.println(message);
		
		}

}
