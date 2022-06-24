package com.example.its.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.its.domain.auth.CustomUserDetails;
import com.example.its.domain.auth.User;

@Controller
public class IndexController {

	@GetMapping
	public String index(@AuthenticationPrincipal OidcUser googleUser, 
			@AuthenticationPrincipal CustomUserDetails springUser) {
//		google 認証時のみ以下使用可能
//    	System.out.println(googleUser.getEmail());
//    	System.out.println(googleUser.getFullName());
		
//		System.out.println(springUser.getUsername());
    	return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return  "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return  "logout";
    }
}
