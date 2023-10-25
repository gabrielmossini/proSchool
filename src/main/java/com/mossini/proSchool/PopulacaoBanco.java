package com.mossini.proSchool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mossini.proSchool.core.dominio.Curso;
import com.mossini.proSchool.core.dominio.CursoRepositorio;
import com.mossini.proSchool.core.dominio.Endereco;
import com.mossini.proSchool.core.dominio.EnderecoRepositorio;
import com.mossini.proSchool.core.dominio.Funcao;
import com.mossini.proSchool.core.dominio.FuncaoRepositorio;
import com.mossini.proSchool.core.dominio.Sexo;
import com.mossini.proSchool.core.dominio.SexoRepositorio;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class PopulacaoBanco implements CommandLineRunner{
	
	@Autowired
	private FuncaoRepositorio funcaoRepo;
	
	@Autowired
	private CursoRepositorio cursoRepo;
	
	@Autowired
	private SexoRepositorio sexoRepo;
	
	@Autowired
	private EnderecoRepositorio enderecoRepo;
	
	@Override
	public void run(String... args) throws Exception{
	}
}
