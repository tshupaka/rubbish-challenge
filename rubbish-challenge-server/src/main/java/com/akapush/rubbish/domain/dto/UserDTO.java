package com.akapush.rubbish.domain.dto;

public class UserDTO {

	private String email;
	private String name;
	private String status;
	private String jwtToken;

	public UserDTO(String email, String name, String status, String jwtToken) {
		super();
		this.email = email;
		this.name = name;
		this.status = status;
		this.jwtToken = jwtToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
