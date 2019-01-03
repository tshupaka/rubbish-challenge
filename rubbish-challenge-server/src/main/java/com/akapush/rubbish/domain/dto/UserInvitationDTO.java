package com.akapush.rubbish.domain.dto;

public class UserInvitationDTO {

	private String email;
	private String message;

	public UserInvitationDTO(String email, String message) {
		super();

		this.email = email;
		this.message = message;
	}

	public UserInvitationDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
