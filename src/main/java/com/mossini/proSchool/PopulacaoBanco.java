package com.mossini.proSchool;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mossini.proSchool.core.dominio.Curso;
import com.mossini.proSchool.core.dominio.CursoRepositorio;
import com.mossini.proSchool.core.dominio.Sexo;
import com.mossini.proSchool.core.dominio.SexoRepositorio;
import com.mossini.proSchool.rh.dominio.Professor;
import com.mossini.proSchool.rh.dominio.ProfessorRepositorio;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class PopulacaoBanco implements CommandLineRunner{
	
	@Autowired
	private ProfessorRepositorio professorRepo;
	
	@Autowired
	private CursoRepositorio cursoRepo;
	
	@Autowired
	private SexoRepositorio sexoRepo;
	
	@Override
	public void run(String... args) throws Exception{
		
		Curso curso1 = new Curso("Nenhum");
		
		cursoRepo.save(curso1);
		cursoRepo.flush();
		
		Sexo sexo1 = new Sexo("Masculino");
		Sexo sexo2 = new Sexo("Feminino");
		Sexo sexo3 = new Sexo("Prefiro Não Dizer");
		Sexo sexo4 = new Sexo("Outro");
		
		sexoRepo.save(sexo1);
		sexoRepo.save(sexo2);
		sexoRepo.save(sexo3);
		sexoRepo.save(sexo4);
		sexoRepo.flush();		
	}
}
