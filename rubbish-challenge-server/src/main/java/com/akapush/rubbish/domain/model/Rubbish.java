package com.akapush.rubbish.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "RUBBISH")
public class Rubbish {

	@Id
	@Column(name = "RBH_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_rubbish_rubbish_rbh_id")
	@SequenceGenerator(name = "seq_rubbish_rubbish_rbh_id", sequenceName = "seq_rubbish_rubbish_rbh_id")

	private Long id;

	@Column(name = "RBH_WEIGHT")
	private Integer weight;
	@Column(name = "RBH_NB_USER")
	private Integer nbUser;
	@Column(name = "RBH_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date date;

	@JoinColumn(name = "USR_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "RUT_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private RubbishType rubbishType;

	public Rubbish() {

	}

	public Rubbish(Long id, Integer weight, Integer nbUser, Date date, User user) {
		super();
		this.id = id;
		this.weight = weight;
		this.nbUser = nbUser;
		this.date = date;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getNbUser() {
		return nbUser;
	}

	public void setNbUser(Integer nbUser) {
		this.nbUser = nbUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RubbishType getRubbishType() {
		return rubbishType;
	}

	public void setRubbishType(RubbishType rubbishType) {
		this.rubbishType = rubbishType;
	}

	@Override
	public String toString() {
		return "Rubbish [id=" + id + ", weight=" + weight + ", nbUser=" + nbUser + ", date=" + date + ", user=" + user
				+ "]";
	}

}
