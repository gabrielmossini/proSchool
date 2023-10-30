package com.mossini.proSchool.rh.dominio;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mossini.proSchool.rh.dominio.dtos.AlunoListaDTO;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Long>{
	
	List<Aluno> findByStatus(String status);
	
/*	@Query("SELECT NEW com.mossini.proSchool.rh.dominio.dtos.AlunoListaDTO(p.id, p.nome, p.telefone, p.telefone1, p.email, p.nomePai, p.nomeMae, p.curso, p.periodo, p.sexo, p.escola, p.ensino, p.endereco, p.rua, p.num, p.status) FROM TBAlunos p LEFT JOIN p.curso d")
	List<AlunoListaDTO> findAllAlunoLista();

	@Query(value = "SELECT new com.mossini.proSchool.rh.dominio.dtos.AlunoListaDTO(p.id, p.nome, p.telefone, p.telefone1, p.email, p.nomePai, p.nomeMae, p.curso, p.periodo, p.sexo, p.escola, p.ensino, p.endereco, p.rua, p.num, p.status)"
	        + " FROM TBAlunos p LEFT JOIN p.curso c LEFT JOIN p.sexo s LEFT JOIN p.escola e LEFT JOIN p.endereco en",
	        countQuery = "SELECT COUNT(p) FROM TBAlunos p")
	Page<AlunoListaDTO> findAllAlunoListaPaginado(Pageable pageable);
*/
}
