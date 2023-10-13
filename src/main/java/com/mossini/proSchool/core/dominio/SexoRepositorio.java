package com.mossini.proSchool.core.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexoRepositorio extends JpaRepository<Sexo, Long>{

}
