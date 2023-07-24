package com.example.blog.payLoads;

import java.util.Objects;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtAuthResponse(String token) {
		super();
		this.token = token;
	}

	public JwtAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JwtAuthResponse [token=" + token + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JwtAuthResponse other = (JwtAuthResponse) obj;
		return Objects.equals(token, other.token);
	}
	
	
	
}
