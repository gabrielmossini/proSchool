package com.mossini.proSchool.core.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long>{	

	List<Curso> findByStatus(String status);
	
	Curso findByNome(String nome);
	
}
