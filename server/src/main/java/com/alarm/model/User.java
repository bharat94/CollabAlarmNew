package com.alarm.model;

import java.util.List;

public class User {
	
	private String name;
	private String username;
	private String password;
	
	public User(String name, String username, String password) {
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
