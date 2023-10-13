package com.mossini.proSchool.core.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mossini.proSchool.core.dominio.Endereco;
import com.mossini.proSchool.core.dominio.EnderecoRepositorio;

import jakarta.validation.Valid;

@Controller
public class EnderecoControle {
	
	private EnderecoRepositorio enderecoRepo;
	
	public EnderecoControle(EnderecoRepositorio enderecoRepo) {
		this.enderecoRepo = enderecoRepo;
	}
	
	@GetMapping("/core/enderecos")
	public String enderecos(Model model) {
		model.addAttribute("listaEnderecos", enderecoRepo.findAll());
		return "core/enderecos/index";
	}
	
	@GetMapping("/core/enderecos/novo")
	public String novoEndereco(Model model){
		model.addAttribute("endereco", new Endereco(""));
		return "core/enderecos/form";
	}
	
	@GetMapping("/core/enderecos/{id}")
	public String alterarEndereco(@PathVariable("id") long id, Model model) {
		Optional<Endereco> enderecoOpt = enderecoRepo.findById(id);
		if (enderecoOpt.isEmpty()) {
			throw new IllegalArgumentException("Endereço Invalido.");
		}
		
		model.addAttribute("endereco", enderecoOpt.get());
		return "core/enderecos/form";
	}
	
	@PostMapping("core/enderecos/salvar")
	public String salvarEndereco(@Valid @ModelAttribute("curso") Endereco endereco, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "core/enderecos/form";
		}
		
		enderecoRepo.save(endereco);
		return "redirect:/core/enderecos";
	}
	
	@GetMapping("/core/enderecos/{id}/details")
	public String showAddressDetails(@PathVariable("id") long id, Model model) {
	    Optional<Endereco> enderecoOpt = enderecoRepo.findById(id);
	    if (enderecoOpt.isEmpty()) {
	        throw new IllegalArgumentException("Endereço não encontrado.");
	    }
	    
	    model.addAttribute("endereco", enderecoOpt.get());
	    return "core/enderecos/details";
	}
	
	@GetMapping("core/enderecos/excluir/{id}")
	public String excluirEndereco(@PathVariable("id") long id) {
		Optional<Endereco> enderecoOpt = enderecoRepo.findById(id);
		if (enderecoOpt.isEmpty()) {
			throw new IllegalArgumentException("Endereço Invalido.");
		}
		
		enderecoRepo.delete(enderecoOpt.get());
		return "redirect:/core/enderecos";
	}
	

}
