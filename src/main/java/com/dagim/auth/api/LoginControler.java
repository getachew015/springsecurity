package com.dagim.auth.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControler {
	
	
	@GetMapping("/login")
	public String viewLogin( ) {
		return "login";
	}
	
	
	@GetMapping("/home")
	public String viewHomePage() {
		return "index";
	}
}
