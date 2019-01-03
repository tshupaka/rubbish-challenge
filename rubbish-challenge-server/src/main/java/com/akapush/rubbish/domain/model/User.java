package com.akapush.rubbish.domain.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_")
public class User {

	@Id
	@Column(name = "USR_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_rubbish_user_usr_id")
	@SequenceGenerator(name = "seq_rubbish_user_usr_id", sequenceName = "seq_rubbish_user_usr_id")
	private Long id;

	@Column(name = "USR_NAME")
	private String name;

	@Column(name = "USR_EMAIL")
	private String email;

	@Column(name = "USR_PASSWORD")
	private String password;

	@Column(name = "USR_UUID_REGISTER")
	private String uuidRegister;

	@Column(name = "USR_ACTION_DATE")
	private Date actionDate;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "USR_STATE")
	private EUserState userState;

	@OneToMany(mappedBy = "user")
	private Set<UserGroup> userGroups;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuidRegister() {
		return uuidRegister;
	}

	public void setUuidRegister(String uuidRegister) {
		this.uuidRegister = uuidRegister;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public EUserState getUserState() {
		return userState;
	}

	public void setUserState(EUserState userState) {
		this.userState = userState;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

}
