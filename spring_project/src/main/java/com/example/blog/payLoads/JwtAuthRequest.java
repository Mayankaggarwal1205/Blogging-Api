package com.example.blog.payLoads;

import java.util.Objects;

import lombok.Data;

//@Data
public class JwtAuthRequest {
	
	private String username;
	
	private String password;
	
	

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

	public JwtAuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JwtAuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JwtAuthRequest [username=" + username + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JwtAuthRequest other = (JwtAuthRequest) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	

}
