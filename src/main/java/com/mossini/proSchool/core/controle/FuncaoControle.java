package com.mossini.proSchool.core.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mossini.proSchool.core.dominio.Funcao;
import com.mossini.proSchool.core.dominio.FuncaoRepositorio;

import jakarta.validation.Valid;

@Controller
public class FuncaoControle {
	
	private FuncaoRepositorio funcaoRepo;
	
	public FuncaoControle(FuncaoRepositorio funcaoRepo) {
		this.funcaoRepo = funcaoRepo;
	}
	
	@GetMapping("/core/funcoes")
	public String funcoes(Model model) {
		model.addAttribute("funcoes", funcaoRepo.findAll());
		return "core/funcoes/index";
	}
	
	@GetMapping("/core/funcoes/novo")
	public String novoFuncao(Model model){
		model.addAttribute("funcao", new Funcao(""));
		return "core/funcoes/form";
	}
	
	@GetMapping("/core/funcoes/{id}")
	public String alterarFuncao(@PathVariable("id") long id, Model model) {
		Optional<Funcao> funcaoOpt = funcaoRepo.findById(id);
		if (funcaoOpt.isEmpty()) {
			throw new IllegalArgumentException("Função Invalida.");
		}
		
		model.addAttribute("funcao", funcaoOpt.get());
		return "core/funcoes/form";
	}
	
	@PostMapping("core/funcoes/salvar")
	public String salvarFuncoes(@Valid @ModelAttribute("funcao") Funcao funcao, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "core/funcoes/form";
		}
		
		funcaoRepo.save(funcao);
		return "redirect:/core/funcoes";
	}
	
	@GetMapping("/core/funcoes/{id}/details")
	public String showFuncoesDetails(@PathVariable("id") long id, Model model) {
	    Optional<Funcao> funcaoOpt = funcaoRepo.findById(id);
	    if (funcaoOpt.isEmpty()) {
	        throw new IllegalArgumentException("Função não encontrada.");
	    }
	    
	    model.addAttribute("funcao", funcaoOpt.get());
	    return "core/funcoes/details";
	}
	
	@GetMapping("core/funcoes/excluir/{id}")
	public String excluirEscola(@PathVariable("id") long id) {
		Optional<Funcao> funcaoOpt = funcaoRepo.findById(id);
		if (funcaoOpt.isEmpty()) {
			throw new IllegalArgumentException("Função Invalida.");
		}
		
		funcaoRepo.delete(funcaoOpt.get());
		return "redirect:/core/funcoes";
	}

}
