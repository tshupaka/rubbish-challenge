package com.akapush.rubbish.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "GROUP_")
@Entity
public class Group {

	@Id
	@Column(name = "GRP_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_rubbish_group_grp_id")
	@SequenceGenerator(name = "seq_rubbish_group_grp_id", sequenceName = "seq_rubbish_group_grp_id")
	private Long id;

	@Column(name = "GRP_NAME")
	private String name;

	@OneToMany(mappedBy = "group")
	private Set<UserGroup> userGroups;

	public Group() {
	}

	public Group(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

}
