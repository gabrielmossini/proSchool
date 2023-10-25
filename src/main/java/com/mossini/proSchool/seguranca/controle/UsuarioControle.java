package com.mossini.proSchool.seguranca.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mossini.proSchool.core.dominio.FuncaoRepositorio;
import com.mossini.proSchool.core.dominio.SexoRepositorio;
import com.mossini.proSchool.seguranca.dominio.Usuario;
import com.mossini.proSchool.seguranca.dominio.UsuarioRepositorio;

import jakarta.validation.Valid;

@Controller
public class UsuarioControle {
	
	private UsuarioRepositorio usuarioRepo;
	private FuncaoRepositorio funcaoRepo;
	private SexoRepositorio sexoRepo;
	
	public UsuarioControle(UsuarioRepositorio usuarioRepo, FuncaoRepositorio funcaoRepo, SexoRepositorio sexoRepo) {
		this.usuarioRepo = usuarioRepo;
		this.funcaoRepo = funcaoRepo;
		this.sexoRepo = sexoRepo;
	}
	
	@GetMapping("/seguranca/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("listaUsuarios", usuarioRepo.findAll());
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("funcoes", funcaoRepo.findAll());
		return "seguranca/usuarios/index";
	}
	
	@GetMapping("/seguranca/usuarios/novo")
	public String novoUsuario(Model model){
		model.addAttribute("usuario", new Usuario(""));
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("funcoes", funcaoRepo.findAll());
		return "seguranca/usuarios/form";
	}
	
	@GetMapping("/seguranca/usuarios/{id}")
	public String alterarUsuario(@PathVariable("id") long id, Model model) {
		Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new IllegalArgumentException("Usuario Invalido.");
		}
		
		model.addAttribute("usuario", usuarioOpt.get());		
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("funcoes", funcaoRepo.findAll());
		return "seguranca/usuarios/form";
	}
	
	@PostMapping("seguranca/usuarios/salvar")
	public String salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("sexos", sexoRepo.findAll());
			model.addAttribute("funcoes", funcaoRepo.findAll());
			return "seguranca/usuarios/form";
		}
	    
//		if (usuarioRepo.existsByUsername(usuario.getUsername())) {
//	        bindingResult.rejectValue("username", "error.username", "Usuário já cadastrado.");
//	        return "seguranca/usuarios/form";
//	    }
		
		usuarioRepo.save(usuario);
		return "redirect:/seguranca/usuarios";
	}
	
	@GetMapping("/seguranca/usuarios/{id}/details")
	public String showUserDetails(@PathVariable("id") long id, Model model) {
	    Optional<Usuario> usuarioOpt = usuarioRepo.findById(id);
	    if (usuarioOpt.isEmpty()) {
	        throw new IllegalArgumentException("Usuario não encontrado.");
	    }
	    
		model.addAttribute("sexos", sexoRepo.findAll());
		model.addAttribute("funcoes", funcaoRepo.findAll());
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
