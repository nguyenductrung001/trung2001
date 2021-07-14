package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(Model model, Principal principal) {
		if (principal != null) {
			return "redirect:/home";
		}
		return "login/login";
	}
	@RequestMapping("/logoutSeccess")
	public String logout() {
		return "redirect:/login";
	
		
	}
	@RequestMapping("/404")
	public String error() {
		return "redirect:/pc/home";
		
	}
	
	
}
