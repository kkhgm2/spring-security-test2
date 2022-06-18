package com.example.its.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class PawwordEncoderConfig {

	@Bean
	public PasswordEncoder PawwordEncoderConfig() {
		return new Pbkdf2PasswordEncoder();		
	}
}
