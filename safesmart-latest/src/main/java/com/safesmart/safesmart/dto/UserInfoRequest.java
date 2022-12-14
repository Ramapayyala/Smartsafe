package com.safesmart.safesmart.dto;

import java.util.regex.Pattern;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;

public class UserInfoRequest {

	
	private Long id;

	private String username;

	private String password;

	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String role;

	private boolean active;

	private String feature;
	
	// admin or manager can create users
	private Long loggedUserId;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	
	public Long getLoggedUserId() {
		return loggedUserId;
	}

	public void setLoggedUserId(Long loggedUserId) {
		this.loggedUserId = loggedUserId;
	}

	public void validateRequiredAttributes() {
		if (username.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Username");
		}
		if (password.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Password");
		}
		if (role.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Role");
		}
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
		if(role.equals("ADMIN") || role.equals("SHIFTMANAGER") || role.equals("MANAGER")) {
			System.out.println("in pin error");
			if (password.length() != 6 || !p.matcher(password).matches()) {
				throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_ADMIN_PIN);
			}	
		}
		else {
		if (password.length() != 4 || !p.matcher(password).matches()) {
			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_PIN);
		}
		}

	}

	public void validateLoginRequired() {
		if (password.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Password");
		}
		String regex = "[0-9]+";
		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
		if (password.length() != 4 || !p.matcher(password).matches()) {
			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_PIN);
		}
		System.out.println(feature);
		if (feature.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Feautre");
		}
	}

	@Override
	public String toString() {
		return "UserInfoRequest [id=" + id + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", mobile=" + mobile + ", role=" + role
				+ ", active=" + active + ", feature=" + feature + ", loggedUserId=" + loggedUserId + "]";
	}
}
