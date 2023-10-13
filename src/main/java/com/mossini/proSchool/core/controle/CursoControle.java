package com.mossini.proSchool.core.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mossini.proSchool.core.dominio.Curso;
import com.mossini.proSchool.core.dominio.CursoRepositorio;

import jakarta.validation.Valid;

@Controller
public class CursoControle {
	
	private CursoRepositorio cursoRepo;
	
	public CursoControle(CursoRepositorio cursoRepo) {
		this.cursoRepo = cursoRepo;
	}
	
	@GetMapping("/core/cursos")
	public String cursos(Model model) {
		model.addAttribute("listaCursos", cursoRepo.findAll());
		return "core/cursos/index";
	}
	
	@GetMapping("/core/cursos/novo")
	public String novoCurso(Model model){
		model.addAttribute("curso", new Curso(""));
		return "core/cursos/form";
	}
	
	@GetMapping("/core/cursos/{id}")
	public String alterarCurso(@PathVariable("id") long id, Model model) {
		Optional<Curso> cursoOpt = cursoRepo.findById(id);
		if (cursoOpt.isEmpty()) {
			throw new IllegalArgumentException("Curso Invalido.");
		}
		
		model.addAttribute("curso", cursoOpt.get());
		return "core/cursos/form";
	}
	
	@PostMapping("core/cursos/salvar")
	public String salvarCurso(@Valid @ModelAttribute("curso") Curso curso, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "core/cursos/form";
		}
		
		cursoRepo.save(curso);
		return "redirect:/core/cursos";
	}
	
	@GetMapping("/core/cursos/{id}/details")
	public String showCoursesDetails(@PathVariable("id") long id, Model model) {
	    Optional<Curso> cursoOpt = cursoRepo.findById(id);
	    if (cursoOpt.isEmpty()) {
	        throw new IllegalArgumentException("Curso não encontrado.");
	    }
	    
	    model.addAttribute("curso", cursoOpt.get());
	    return "core/cursos/details";
	}
	
	@GetMapping("core/cursos/excluir/{id}")
	public String excluirCurso(@PathVariable("id") long id) {
		Optional<Curso> cursoOpt = cursoRepo.findById(id);
		if (cursoOpt.isEmpty()) {
			throw new IllegalArgumentException("Curso Invalido.");
		}
		
		cursoRepo.delete(cursoOpt.get());
		return "redirect:/core/cursos";
	}
}
