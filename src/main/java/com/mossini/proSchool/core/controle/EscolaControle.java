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

import com.mossini.proSchool.core.dominio.Escola;
import com.mossini.proSchool.core.dominio.EscolaRepositorio;
import com.mossini.proSchool.core.service.EscolaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class EscolaControle {

	private EscolaRepositorio escolaRepo;
	private final EscolaService escolaService;

	public EscolaControle(EscolaRepositorio escolaRepo, EscolaService escolaService) {
		this.escolaRepo = escolaRepo;
		this.escolaService = escolaService;
	}

	@GetMapping("/core/escolas")
	public String escolas(Model model) {
		model.addAttribute("listaEscolas", escolaRepo.findAll());
		return "core/escolas/index";
	}

	@GetMapping("/core/escolas/novo")
	public String novoEscola(Model model) {
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
	public String salvarEscolas(@Valid @ModelAttribute("escola") Escola escola, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "core/escolas/form";
		}

		Escola escolaEncontrado = escolaRepo.findByNome(escola.getNome());
		if (escolaEncontrado != null & escolaEncontrado.getId() != escola.getId()) {
			bindingResult.addError(new FieldError("escola", "nome", "Escola já cadastrada."));
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

	@GetMapping("/pdf/escola")
	public void generatePDF(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Escola_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Escola> escolas = escolaRepo.findAll();

		escolaService.export(response, escolas);
	}

}
