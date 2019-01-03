package com.akapush.rubbish.domain.exception;

import com.akapush.rubbish.domain.model.User;

public class UserAlreadExistsOnGroup extends Exception {

	private static final long serialVersionUID = 1L;

	private final User user;

	public UserAlreadExistsOnGroup(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
