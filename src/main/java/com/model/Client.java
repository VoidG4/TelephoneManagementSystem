package com.model;

public class Client extends User {
	private String sellerUsername;
	private final String AFM;
	private PhoneNumber phoneNumber;
	
	//Constructors
	public Client(String AFM) {
		super();
		this.AFM = AFM;
	}
	
	public Client() {
		this("");
	}
	
	//Methods
	@Override
	public String toString() {
		return String.format(super.toString() + ", AFM: %s, phoneNumber: %s", AFM, phoneNumber.toString());
	}
	
	//Setters
	public void setSellerUsername(String sellerUsername) {
		this.sellerUsername = sellerUsername;
	}
	
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//Getters
	public String getAFM() {
		return AFM;
	}
	
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getSellerUsername() {
		return sellerUsername;
	}
}
