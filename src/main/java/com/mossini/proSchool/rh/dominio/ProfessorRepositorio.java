package com.mossini.proSchool.rh.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mossini.proSchool.seguranca.dominio.Usuario;


@Repository
public interface ProfessorRepositorio extends JpaRepository<Professor, Long>{
	
}
