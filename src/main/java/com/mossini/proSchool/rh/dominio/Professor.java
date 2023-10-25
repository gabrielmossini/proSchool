package com.mossini.proSchool.rh.dominio;

import com.mossini.proSchool.core.dominio.Curso;
import com.mossini.proSchool.seguranca.dominio.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBProfessor")
public class Professor{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=true)
	private Usuario usuario;
	
	@ManyToOne(optional=true)
	private Curso curso;

//	@ManyToOne(optional=true)
//	private Atividade atividade;
	
	@Deprecated
	public Professor() {}
	
	public Professor(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
