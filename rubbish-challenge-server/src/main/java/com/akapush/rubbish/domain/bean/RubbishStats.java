package com.akapush.rubbish.domain.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RubbishStats implements Comparable<RubbishStats> {

	private Date date;
	private String name;
	private Integer rubbisweight;

	@JsonIgnore
	private Integer totalWeight = 0;
	@JsonIgnore
	private Integer nbUser = 0;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRubbisweight() {
		return rubbisweight;
	}

	public void setRubbisweight(Integer rubbisweight) {
		this.rubbisweight = rubbisweight;
	}

	public Integer getNbUser() {
		return nbUser;
	}

	public void setNbUser(Integer nbUser) {
		this.nbUser = nbUser;
	}

	public Integer getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	@Override
	public String toString() {
		return "RubbishStats [name=" + name + ", rubbisweight=" + rubbisweight + ", totalWeight=" + totalWeight
				+ ", nbUser=" + nbUser + "]";
	}

	public void addRubbishToTotal(Integer weight, Integer nbUser) {
		this.totalWeight += weight;
		this.nbUser += nbUser;
	}

	@Override
	public int compareTo(RubbishStats other) {
		return this.rubbisweight.compareTo(other.rubbisweight);
	}

}
