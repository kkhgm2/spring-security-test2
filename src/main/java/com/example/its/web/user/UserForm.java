package com.example.its.web.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.its.web.validation.UniquUsername;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserForm {
	
	@NotBlank
	@UniquUsername
	private String username;
	
	@NotBlank
	@Size(min=12, max = 128)
	private String password;
}
