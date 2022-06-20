package com.example.its.web.user;

import java.util.Collections;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.its.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;

    @GetMapping
    public String showList(Model model) {
    	
    	model.addAttribute("userList", service.findAll());
    	
        return "users/list";
    }
    
    @GetMapping("/creationForm")
    public String showcreationForm(@ModelAttribute UserForm form) {
    	
        return "users/creationForm";
    }
    
    @PostMapping
    public String create(@Validated UserForm form, BindingResult bindingResult) { 
    	if(bindingResult.hasErrors()) {
    		return showcreationForm(form);
    	}
    	
    	service.create(form.getUsername(), form.getPassword());
    	
    	System.out.println(form.toString());
 
    	return "redirect:/users";
    }

}
