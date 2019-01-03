package com.akapush.rubbish.domain.bean;

import java.util.Date;
import java.util.List;

public class HistoGroup implements Comparable<HistoGroup> {

	private Date date;

	private List<HistoGroupWeight> values;

	public HistoGroup(Date date, List<HistoGroupWeight> values) {
		super();
		this.date = date;
		this.values = values;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<HistoGroupWeight> getValues() {
		return values;
	}

	public void setValues(List<HistoGroupWeight> values) {
		this.values = values;
	}

	@Override
	public int compareTo(HistoGroup o) {
		if (o == null) {
			return -1;
		}
		return getDate().compareTo(o.getDate());
	}

}
