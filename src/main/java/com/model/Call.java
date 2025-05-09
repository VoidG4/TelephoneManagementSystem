package com.model;

import java.sql.Timestamp;


public class Call {
	private int id;
	private int duration;
	private Timestamp time;
	
	//Constructors
	public Call(int id, int duration, Timestamp time) {
		setId(id);
		setDuration(duration);
		setTime(time);
	}
	
	public Call() {
		this(0, 0, null);
	}
	
	//Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	//Getters
	public int getId() {
		return id;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public Timestamp getTime() {
		return time;
	}
}
