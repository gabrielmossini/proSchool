package com.mossini.proSchool.seguranca.dominio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Long>{

    boolean existsByUsername(String username);
    
	Usuario findByUsername(String username);

	Usuario findByCpf(String cpf);
	
	List<Usuario> findByStatus(String status);

}
