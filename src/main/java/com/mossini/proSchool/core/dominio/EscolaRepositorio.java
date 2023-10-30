package com.mossini.proSchool.core.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaRepositorio extends JpaRepository< Escola, Long> {

	List<Escola> findByEnsino(String ensino);
	
	Escola findByNome(String nome);

}
