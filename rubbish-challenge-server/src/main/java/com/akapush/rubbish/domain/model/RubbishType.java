package com.akapush.rubbish.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RUBBISH_TYPE")
public class RubbishType {

	@Id
	@Column(name = "RUT_ID")
	private Long id;

	@Column(name = "RUT_LABEL")
	private String label;

	public RubbishType() {
		super();
	}

	public RubbishType(Long id, String label) {
		super();
		this.id = id;
		this.label = label;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
