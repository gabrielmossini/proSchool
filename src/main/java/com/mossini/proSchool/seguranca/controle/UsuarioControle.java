package com.mossini.proSchool.seguranca.controle;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mossini.proSchool.seguranca.dominio.Usuario;
import com.mossini.proSchool.seguranca.dominio.UsuarioRepositorio;

import jakarta.validation.Valid;

public class UsuarioControle {
	
	private UsuarioRepositorio usuarioRepo;
	
	public UsuarioControle(UsuarioRepositorio usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	@GetMapping("/seguranca/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("listaUsuarios", usuarioRepo.findAll());
		return "seguranca/usuarios/index";
	}
	
	@GetMapping("/seguranca/usuarios/novo")
	public String novoUsuario(Model model){
		model.addAttribute("usuario", new Usuario(""));
		return "seguranca/usuarios/form";
	}
	
	@GetMapping("/seguranca/usuarios/{id}")
	public String alterarUsuario(@PathVariable("id") long id, Model model) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new IllegalArgumentException("Usuario Invalido.");
		}
		
		model.addAttribute("usuario", usuarioOpt.get());
		return "seguranca/usuarios/form";
	}
	
	@PostMapping("seguranca/usuarios/salvar")
	public String salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "seguranca/usuarios/form";
		}
		
		usuarioRepo.save(usuario);
		return "redirect:/seguranca/usuarios";
	}
	
	@GetMapping("/seguranca/usuarios/{id}/details")
	public String showUserDetails(@PathVariable("id") long id, Model model) {
	    Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
	    if (usuarioOpt.isEmpty()) {
	        throw new IllegalArgumentException("Usuario não encontrado.");
	    }
	    
	    model.addAttribute("usuario", usuarioOpt.get());
	    return "seguranca/usuarios/details";
	}
	
	@GetMapping("seguranca/usuarios/excluir/{id}")
	public String excluirUsuario(@PathVariable("id") long id) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new IllegalArgumentException("Usuario Invalido.");
		}
		
		usuarioRepo.delete(usuarioOpt.get());
		return "redirect:/seguranca/usuarios";
	}


}
