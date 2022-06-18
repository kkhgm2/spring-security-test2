package com.example.its.domain.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.its.domain.auth.User;
import com.example.its.domain.auth.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repo;
	
	private final PasswordEncoder passwordEncoder;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public void create (String username, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		repo.insert(username, encodedPassword);
	}
}
