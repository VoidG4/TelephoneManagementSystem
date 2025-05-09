package com.model;

public abstract class User {
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String property;
	private String salt;
	public static int usersCounter = 0;
	
	//Constructors
	public User(String username, String password, String email,String name, String surname, String property, String salt) {
		setUsername(username);
		setPassword(password);
		setName(name);
		setSurname(surname);
		setProperty(property);
		setSalt(salt);
		usersCounter++;
	}
	
	public User() {
		this("", "", "", "", "", "", "");
	}
	
	//Methods
	@Override
	public String toString() {
		return String.format("username: %s, password: %s, name: %s, surname: %s, property: %s", username, password, name, surname, property);
	}
	
	//Setters
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setProperty(String property) {
		if(property == "Client" || property == "Administrator" || property == "Seller") {
			this.property = property;
		} else {
			this.property = "";
		}
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	//Getters
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getProperty() {
		return property;
	}
	
	public String getSalt() {
		return salt;
	}
}
