package com.akapush.rubbish.domain.dto;

public class UserRubbishSummaryDTO {
	private String username;
	private double weight30d;
	private double weight365d;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getWeight30d() {
		return weight30d;
	}

	public void setWeight30d(double weight30d) {
		this.weight30d = weight30d;
	}

	public double getWeight365d() {
		return weight365d;
	}

	public void setWeight365d(double weight365d) {
		this.weight365d = weight365d;
	}

}
