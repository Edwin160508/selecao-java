package com.edwin.works.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioInputModel {

	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
	private String perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	
}
