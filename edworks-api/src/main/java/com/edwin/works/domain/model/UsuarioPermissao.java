package com.edwin.works.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table(name = "usuario_permissao", schema = "edworks")
@Entity
@IdClass(UsuarioPermissaoPK.class)
public class UsuarioPermissao implements Serializable{

	
	private static final long serialVersionUID = -1800583518619501953L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "permissao_id")
	private Permissao permissao;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	
}
