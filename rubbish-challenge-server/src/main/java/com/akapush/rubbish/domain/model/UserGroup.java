package com.akapush.rubbish.domain.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER_GROUP")
public class UserGroup {

	@Id
	@Column(name = "UGP_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_rubbish_user_group_ugp_id")
	@SequenceGenerator(name = "seq_rubbish_user_group_ugp_id", sequenceName = "seq_rubbish_user_group_ugp_id")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USR_ID")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "GRP_ID")
	private Group group;

	@Enumerated(EnumType.STRING)
	@Column(name = "UGP_STATE")
	private EUserGroupState userGroupState;

	@Column(name = "UGP_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public UserGroup() {
		super();
	}

	public UserGroup(Long id, User user, Group group) {
		super();
		this.id = id;
		this.user = user;
		this.group = group;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public EUserGroupState getUserGroupState() {
		return userGroupState;
	}

	public void setUserGroupState(EUserGroupState userGroupState) {
		this.userGroupState = userGroupState;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
