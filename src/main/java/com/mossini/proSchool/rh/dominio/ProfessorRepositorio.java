package com.mossini.proSchool.rh.dominio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProfessorRepositorio extends JpaRepository<Professor, Long>{
	
}
