package com.akapush.rubbish.security;

/**
 * Classe utilisé pour transcoder le flux d'authentification JSON en java
 * 
 * @author eric
 *
 */
public class AccountCredentials {

	private String email;
	private String password;

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

}