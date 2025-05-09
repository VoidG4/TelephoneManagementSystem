package com.model;

public class Bill {
	private int id;
	private String month;
	private float payment;
	
	//Constructors
	public Bill(int id, String month, float payment) {
		setId(id);
		setMonth(month);
		setPayment(payment);
	}
	
	public Bill() {
		this(0, "", 0);
	}
	
	//Setters
	public void setMonth(String month) {
		this.month = month;
	}
	
	public void setPayment(float payment) {
		this.payment = payment;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	//Getters
	public String getMonth() {
		return month;
	}
	
	public float getPayment() {
		return payment;
	}
	
	public int getId() {
		return id;
	}
}
