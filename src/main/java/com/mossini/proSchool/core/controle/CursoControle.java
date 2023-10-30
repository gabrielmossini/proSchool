package com.mossini.proSchool.core.controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.mossini.proSchool.core.service.CursoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class CursoControle {
	
	private CursoRepositorio cursoRepo;
	private final CursoService cursoService;
	
	public CursoControle(CursoRepositorio cursoRepo, CursoService cursoService) {
		this.cursoRepo = cursoRepo;
		this.cursoService = cursoService;
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
		
		Curso cursoEncontrado = cursoRepo.findByNome(curso.getNome());
		if (cursoEncontrado != null && cursoEncontrado.getId() != curso.getId()) {
			bindingResult.addError(new FieldError("curso", "nome", "Nome do Curso já cadastrado."));
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
	
    @GetMapping("/pdf/curso")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Curso_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        List<Curso> cursos = cursoRepo.findAll();

        cursoService.export(response, cursos);
    }
	
}
