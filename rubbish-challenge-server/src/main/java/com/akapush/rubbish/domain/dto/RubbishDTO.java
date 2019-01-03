package com.akapush.rubbish.domain.dto;

import java.util.Date;

public class RubbishDTO {

	private Date date;
	private Integer weight;
	private Integer number;
	private Integer type;
	private String labelType;

	public RubbishDTO() {
	}

	public RubbishDTO(Date date, Integer weight, Integer number, Integer type, String labelType) {
		super();
		this.date = date;
		this.weight = weight;
		this.number = number;
		this.type = type;
		this.labelType = labelType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

}
