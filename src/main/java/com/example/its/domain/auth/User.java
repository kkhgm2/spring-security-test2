package com.example.its.domain.auth;

import lombok.Data;

@Data
public class User {
	private String username;
	private String password;
	private Authority authority;
	
	public enum Authority {
		ADMIN,
		USER,
	}
}
