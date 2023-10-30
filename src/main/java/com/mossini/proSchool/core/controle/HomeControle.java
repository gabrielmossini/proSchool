package com.mossini.proSchool.core.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControle {
	
	@GetMapping("/")
	public String greeting() {	
		return "/home/index";
	}
	
	@GetMapping("/alunos/")
	public String contAluno() {
		return "/home/alunos";
	}
	
	@GetMapping("/professores/")
	public String homeProfessor() {
		return "/home/professores";
	}
	
	@GetMapping("/administracao/")
	public String admin() {
		return "/home/admin";
	}
	
	@GetMapping("/construcao/")
	public String construcao() {
		return "/home/build";
	}
/**
	@GetMapping("/login")
	public String login() {
		return "login";
	}*/
}
