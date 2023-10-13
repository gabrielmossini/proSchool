package com.mossini.proSchool.core.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControle {
	
	@GetMapping("/")
	public String greeting() {
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
