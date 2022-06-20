package com.example.its.domain.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
//		if("tom".equals(username)) {
//			return new CustomUserDetails("tom", "password", Collections.emptyList());
//		}
		
		return repo.findByUsername(username)
				.map(user -> 
					new CustomUserDetails(
						user.getUsername(), 
						user.getPassword(), 
						//複数権限を持つ事が想定されるので、リストになっている
						toGrantedAuthorityList(user.getAuthority())
					)
				)
				.orElseThrow( () ->
					new UsernameNotFoundException(
						"given username is not found . (username = '" + username + "')"
					)
				);
	}

	private List<GrantedAuthority> toGrantedAuthorityList(User.Authority authority) {
		
		return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
	}
}
