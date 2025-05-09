package com.model;

public class Administrator extends User{
	
	//Constructors
	public Administrator(String username, String password,String email ,String name, String surname, String property, String salt) {
		super(username, password, email, name, surname, property, salt);
	}
	
	public Administrator() {
		this("", "", "", "", "", "", "");
	}
}
