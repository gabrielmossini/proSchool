package com.mossini.proSchool.rh.controle;

import java.util.List;
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
import com.mossini.proSchool.core.dominio.EnderecoRepositorio;
import com.mossini.proSchool.core.dominio.EscolaRepositorio;
import com.mossini.proSchool.core.dominio.SexoRepositorio;
import com.mossini.proSchool.rh.dominio.Aluno;
import com.mossini.proSchool.rh.dominio.AlunoRepositorio;

import jakarta.validation.Valid;

@Controller
public class AlunoControle {
	
	private AlunoRepositorio alunoRepo;
	private CursoRepositorio cursoRepo;
	private SexoRepositorio sexoRepo;
	private EscolaRepositorio escolaRepo;
	private EnderecoRepositorio enderecoRepo;
	
	public AlunoControle(AlunoRepositorio alunoRepo, CursoRepositorio cursoRepo, SexoRepositorio sexoRepo, EscolaRepositorio escolaRepo, EnderecoRepositorio enderecoRepo) {
		this.alunoRepo = alunoRepo;
		this.cursoRepo = cursoRepo;
		this.sexoRepo = sexoRepo;
		this.escolaRepo = escolaRepo;
		this.enderecoRepo = enderecoRepo;
	}
	
	@GetMapping("/rh/alunos")
	public String alunos(Model model) {
		model.addAttribute("listaAlunos", alunoRepo.findAll());
		model.addAttribute("cursos", cursoRepo.findAll());
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("escolas", escolaRepo.findAll());
		model.addAttribute("enderecos", enderecoRepo.findAll());
		return "rh/alunos/index";
	}
	
	@GetMapping("/rh/alunos/novo")
	public String novoAluno(Model model){
		model.addAttribute("aluno", new Aluno(""));
	    List<Curso> activatedCursos = cursoRepo.findByStatus("Ativo");
		model.addAttribute("cursos", activatedCursos);
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("escolas", escolaRepo.findAll());
		model.addAttribute("enderecos", enderecoRepo.findAll());
		
		return "rh/alunos/form";
	}
	
	@GetMapping("/rh/alunos/{id}")
	public String alterarAluno(@PathVariable("id") long id, Model model) {
		Optional<Aluno> alunoOpt = alunoRepo.findById(id);
		if (alunoOpt.isEmpty()) {
			throw new IllegalArgumentException("Aluno Invalido.");
		}
	    List<Curso> activatedCursos = cursoRepo.findByStatus("Ativo");		
		model.addAttribute("aluno", alunoOpt.get());
		model.addAttribute("cursos", activatedCursos);
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("escolas", escolaRepo.findAll());
		model.addAttribute("enderecos", enderecoRepo.findAll());
		return "rh/alunos/form";
	}
	
	@PostMapping("rh/alunos/salvar")
	public String salvarAluno(@Valid @ModelAttribute("aluno") Aluno aluno, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
		    List<Curso> activatedCursos = cursoRepo.findByStatus("Ativo");
			model.addAttribute("cursos", activatedCursos);
			model.addAttribute("sexos", sexoRepo.findAll());
			model.addAttribute("escolas", escolaRepo.findAll());
			model.addAttribute("enderecos", enderecoRepo.findAll());
			return "rh/alunos/form";
		}
		
		alunoRepo.save(aluno);
		return "redirect:/rh/alunos";
	}
	
	@GetMapping("/rh/alunos/{id}/details")
	public String showStudentDetails(@PathVariable("id") long id, Model model) {
	    Optional<Aluno> alunoOpt = alunoRepo.findById(id);
	    if (alunoOpt.isEmpty()) {
	        throw new IllegalArgumentException("Aluno não encontrado.");
	    }
	    
	    model.addAttribute("aluno", alunoOpt.get());
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("escolas", escolaRepo.findAll());
		model.addAttribute("enderecos", enderecoRepo.findAll());
	    return "rh/alunos/details";
	}
	
	@GetMapping("rh/alunos/excluir/{id}")
	public String excluirAluno(@PathVariable("id") long id) {
		Optional<Aluno> alunoOpt = alunoRepo.findById(id);
		if (alunoOpt.isEmpty()) {
			throw new IllegalArgumentException("Aluno Invalido.");
		}
		
		alunoRepo.delete(alunoOpt.get());
		return "redirect:/rh/alunos";
	}
}