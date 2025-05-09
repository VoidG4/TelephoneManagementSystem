package com.model;

public class PhoneNumber {
	private int id;
	private String number;
	
	//Constructors
	public PhoneNumber(String number) {
		setNumber(number);
	}
	
	public PhoneNumber() {
		this("");
	}
	
	@Override
	public String toString() {
		return number;
	}
	
	//Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	//Getters
	public int getId() {
		return id;
	}
	
	public String getNumber() {
		return number;
	}
}
