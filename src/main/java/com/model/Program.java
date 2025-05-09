package com.model;

public class Program {
	private int id;
	private String nameOfProgram;
	private int speechTime;
	private int smsNumber;
	private int dataNumber;
	private float cost;
	private String description;
	private int duration;
	
	//Constructors
	public Program(int id, String nameOfProgram, int speechTime, int smsNumber, int dataNumber, float cost, String description, int duration) {
		setId(id);
		setNameOfProgram(nameOfProgram);
		setSpeechTime(speechTime);
		setSmsNumber(smsNumber);
		setDataNumber(dataNumber);
		setCost(cost);
		setDescription(description);
		setDuration(duration);
	}
	
	public Program() {
		this(0,"", 0,0,0,0,"", 0);
	}
	
	//Setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNameOfProgram(String nameOfProgram) {
		this.nameOfProgram = nameOfProgram;
	}
	
	public void setSpeechTime(int speechTime) {
		this.speechTime = speechTime;
	}
	
	public void setSmsNumber(int smsNumber) {
		this.smsNumber = smsNumber;
	}
	
	public void setDataNumber(int dataNumber) {
		this.dataNumber = dataNumber;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	//Getters
	public int getId() {
		return id;
	}
	
	public String getNameOfProgram() {
		return nameOfProgram;
	}
	
	public int getSpeechTime() {
		return speechTime;
	}
	
	public int getSmsNumber() {
		return smsNumber;
	}
	
	public int getDataNumber() {
		return dataNumber;
	}
	
	public float getCost() {
		return cost;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getDuration() {
		return duration;
	}
}
