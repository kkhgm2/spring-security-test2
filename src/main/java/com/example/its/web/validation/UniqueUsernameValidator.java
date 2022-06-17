package com.example.its.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.its.domain.auth.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniquUsername, String>{

	private final UserRepository repo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.findByUsername(value).isEmpty();
	}

}
