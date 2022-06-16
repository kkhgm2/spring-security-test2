package com.example.its.domain.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//適切なユーザーを返す. user:tom, pass:password
		//customUserDetails でラッピングされた値と、入力値を比較？
		if("tom".equals(username)) {
			return new CustomUserDetails("tom", "password", Collections.emptyList());
		}
		
		return repo.findByUsername(username)
				.map(user -> 
					new CustomUserDetails(
						user.getUsername(), 
						user.getPassword(), 
						Collections.emptyList()
					)
				)
				.orElseThrow( () ->
					new UsernameNotFoundException(
						"given username is not found . (username = '" + username + "')"
					)
				);
	}
}
