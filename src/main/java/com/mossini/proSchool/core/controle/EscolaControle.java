package com.mossini.proSchool.core.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mossini.proSchool.core.dominio.Escola;
import com.mossini.proSchool.core.dominio.EscolaRepositorio;

import jakarta.validation.Valid;

@Controller
public class EscolaControle {
	
	private EscolaRepositorio escolaRepo;
	
	public EscolaControle(EscolaRepositorio escolaRepo) {
		this.escolaRepo = escolaRepo;
	}
	
	@GetMapping("/core/escolas")
	public String escolas(Model model) {
		model.addAttribute("listaEscolas", escolaRepo.findAll());
		return "core/escolas/index";
	}
	
	@GetMapping("/core/escolas/novo")
	public String novoEscola(Model model){
		model.addAttribute("escola", new Escola(""));
		return "core/escolas/form";
	}
	
	@GetMapping("/core/escolas/{id}")
	public String alterarEscola(@PathVariable("id") long id, Model model) {
		Optional<Escola> escolaOpt = escolaRepo.findById(id);
		if (escolaOpt.isEmpty()) {
			throw new IllegalArgumentException("Escola Invalida.");
		}
		
		model.addAttribute("escola", escolaOpt.get());
		return "core/escolas/form";
	}
	
	@PostMapping("core/escolas/salvar")
	public String salvarEscolas(@Valid @ModelAttribute("escola") Escola escola, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "core/escolas/form";
		}
		
		escolaRepo.save(escola);
		return "redirect:/core/escolas";
	}
	
	@GetMapping("/core/escolas/{id}/details")
	public String showSchoolDetails(@PathVariable("id") long id, Model model) {
	    Optional<Escola> escolaOpt = escolaRepo.findById(id);
	    if (escolaOpt.isEmpty()) {
	        throw new IllegalArgumentException("Escola não encontrada.");
	    }
	    
	    model.addAttribute("escola", escolaOpt.get());
	    return "core/escolas/details";
	}
	
	@GetMapping("core/escolas/excluir/{id}")
	public String excluirEscola(@PathVariable("id") long id) {
		Optional<Escola> escolaOpt = escolaRepo.findById(id);
		if (escolaOpt.isEmpty()) {
			throw new IllegalArgumentException("Escola Invalida.");
		}
		
		escolaRepo.delete(escolaOpt.get());
		return "redirect:/core/escolas";
	}
}
