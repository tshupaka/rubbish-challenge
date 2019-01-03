package com.akapush.rubbish.domain.dto;

import java.util.Date;
import java.util.List;

public class HistoGroupDTO {

	private Date date;

	private List<HistoGroupWeightDTO> values;

	public HistoGroupDTO(Date date, List<HistoGroupWeightDTO> values) {
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

	public List<HistoGroupWeightDTO> getValues() {
		return values;
	}

	public void setValues(List<HistoGroupWeightDTO> values) {
		this.values = values;
	}

}
