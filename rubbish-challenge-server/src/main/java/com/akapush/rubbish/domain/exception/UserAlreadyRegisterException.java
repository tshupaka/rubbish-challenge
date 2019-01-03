package com.akapush.rubbish.domain.exception;

public class UserAlreadyRegisterException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String email;

	public UserAlreadyRegisterException(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
