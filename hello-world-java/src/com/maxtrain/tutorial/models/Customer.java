package com.maxtrain.tutorial.models;

public class Customer {

	private int id; //can set default by doing ...id = 0;
	private String name; //best practice to organize props together and getters/setters together
	private double sales;
	
	public void Print() throws Exception { //methods between props and getters/setters because those don't need to look at much
		var trueOrFalse = true;
		if(!trueOrFalse) {
				throw new Exception();
		}
	}
	
	public void Debug() throws Exception {
		Print();
	}
	
	public Customer(String name) {
		this.setName(name);
	}
	
	public Customer(String name, double sales) {
		this(name); //using constructor above
		this.setSales(sales);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) { //don't have to make parameter name the same, but makes sense to do that
		
		this.id = id; //changes the value of whatever is being passed in
	}
	public String getName() { //can change access modifier later if want/need to
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		if(sales < 0) { //can add/edit features within prop here instead of creating method like in C#
			//do something here and return
		}
		this.sales = sales;
	}
	
	public Customer() { //need constructor to set ID in other part of package because private
		this.id = 1;
	}
}
