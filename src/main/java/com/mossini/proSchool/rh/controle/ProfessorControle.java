package com.mossini.proSchool.rh.controle;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mossini.proSchool.core.dominio.Curso;
import com.mossini.proSchool.core.dominio.CursoRepositorio;
import com.mossini.proSchool.rh.dominio.Professor;
import com.mossini.proSchool.rh.dominio.ProfessorRepositorio;
import com.mossini.proSchool.seguranca.dominio.Usuario;
import com.mossini.proSchool.seguranca.dominio.UsuarioRepositorio;

import jakarta.validation.Valid;

@Controller
public class ProfessorControle {
	
	private ProfessorRepositorio professorRepo;
	private CursoRepositorio cursoRepo;
	private UsuarioRepositorio usuarioRepo;
	
	public ProfessorControle(ProfessorRepositorio professorRepo, CursoRepositorio cursoRepo, UsuarioRepositorio usuarioRepo) {
		this.professorRepo = professorRepo;
		this.cursoRepo = cursoRepo;
		this.usuarioRepo = usuarioRepo;			
	}
	
	@GetMapping("/rh/professores")
	public String professores(Model model) {
		model.addAttribute("listaProfessores", professorRepo.findAll());
		model.addAttribute("cursos", cursoRepo.findAll());
		model.addAttribute("usuarios", usuarioRepo.findAll());
		return "rh/professores/index";
	}
	
	@GetMapping("/rh/professores/novo")
	public String novoProfessor(Model model) {
		model.addAttribute("professor", new Professor());
		List<Usuario> Professor = usuarioRepo.findByStatus("Sim");
		model.addAttribute("usuarios", Professor);
	    List<Curso> activatedCursos = cursoRepo.findByStatus("Ativo");
		model.addAttribute("cursos", activatedCursos);		
		return "rh/professores/form";
	}
	
	@GetMapping("/rh/professores/{id}")
	public String alterarProfessor(@PathVariable("id") long id, Model model) {
		Optional<Professor> professorOpt = professorRepo.findById(id);
		if (professorOpt.isEmpty()) {
			throw new IllegalArgumentException("Professor Invalido.");
		}
		
		model.addAttribute("professor", professorOpt.get());
		List<Usuario> Professor = usuarioRepo.findByStatus("Sim");
		model.addAttribute("usuarios", Professor);
	    List<Curso> activatedCursos = cursoRepo.findByStatus("Ativo");
		model.addAttribute("cursos", activatedCursos);
		return "rh/professores/form";
	}
	
	@PostMapping("rh/professores/salvar")
	public String salvarProfessor(@Valid @ModelAttribute("professor") Professor professor, Curso curso, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<Usuario> Professor = usuarioRepo.findByStatus("Sim");
			model.addAttribute("usuarios", Professor);
		    List<Curso> activatedCursos = cursoRepo.findByStatus("Ativo");
			model.addAttribute("cursos", activatedCursos);
			return "rh/professores/form";
		}
		
		professorRepo.save(professor);
		return "redirect:/rh/professores";
	}
	
	@GetMapping("/rh/professores/{id}/details")
	public String showTeacherDetails(@PathVariable("id") long id, Model model) {
		Optional<Professor> professorOpt = professorRepo.findById(id);
		if (professorOpt.isEmpty()) {
			throw new IllegalArgumentException("Professor Invalido.");
		}
		
		model.addAttribute("professor", professorOpt.get());
		return "rh/professores/details";
	}
	
	@GetMapping("rh/professores/excluir/{id}")
	public String excluirProfessor(@PathVariable("id") long id) {
		Optional<Professor> professorOpt = professorRepo.findById(id);
		if (professorOpt.isEmpty()) {
			throw new IllegalArgumentException("Professor Invalido.");
		}
		
		professorRepo.delete(professorOpt.get());
		return "redirect:/rh/professores";
	}
	
}
