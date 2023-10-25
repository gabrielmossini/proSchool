package com.mossini.proSchool.rh.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Long>{
	
	List<Aluno> findByStatus(String status);
	
}
