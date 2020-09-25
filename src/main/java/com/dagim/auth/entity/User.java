package com.dagim.auth.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="user_tbl")
public class User {
	
	@Id
	private Long userId;
	
	private String userName, userRole, userEmail, userPassword, hireDate;
	

	public User() {
		
	}

	
	public User(String userName, Long userId, String userRole, String userEmail, String userPassword, String hireDate) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.hireDate = hireDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = Long.parseLong(userId);
	}
	public List<String> getUserRole() {
		if(this.userRole.length() > 0)
			return Arrays.asList(this.userRole.split(","));
		return new ArrayList<>();
	}
	public void setUserRole(String userRole) {
		
		this.userRole = userRole;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		
		this.userPassword = passwordEncoder().encode(userPassword);
	}

	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	
	private PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
