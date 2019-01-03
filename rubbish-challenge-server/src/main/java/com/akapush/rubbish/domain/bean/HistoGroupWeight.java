package com.akapush.rubbish.domain.bean;

public class HistoGroupWeight {

	private String username;
	
	private int weight;
	
	

	public HistoGroupWeight(String username, int weight) {
		super();
		this.username = username;
		this.weight = weight;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	
}
