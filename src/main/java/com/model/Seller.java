package com.model;


public class Seller extends User{
	private String adminUsername;
	//Constructors
	public Seller(String username, String password,String email, String name, String surname, String property, String salt) {
		super(username, password, email, name, surname, property, salt);
	}
	
	public Seller() {
		this("", "", "", "", "", "", "");
	}
	
	//Setters, Getters
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	
	public String getAdminUsername() {
		return adminUsername;
	}
}
