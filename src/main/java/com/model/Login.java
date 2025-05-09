package com.model;

public class Login {
	String username, password, property;
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getProperty() {
		return property;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	@Override
    public String toString() {
        return "Login [Username=" + username + "]";
    }
}
