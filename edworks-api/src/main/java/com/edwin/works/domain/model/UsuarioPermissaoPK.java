package com.edwin.works.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioPermissaoPK implements Serializable{

	
	private static final long serialVersionUID = 830297374362182013L;
	
	
	private Long usuario;
		
	private Long permissao;
	
	public UsuarioPermissaoPK (Long usuario,Long permissao) {
		this.usuario = usuario;
		this.permissao = permissao;
	}
	
	public UsuarioPermissaoPK() {}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public Long getPermissao() {
		return permissao;
	}

	public void setPermissao(Long permissao) {
		this.permissao = permissao;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || getClass()!=obj.getClass()) {
			return false;
		}
		UsuarioPermissaoPK pk = (UsuarioPermissaoPK) obj;
		return Objects.equals(usuario, pk.usuario) && 
				Objects.equals(permissao, pk.permissao);
	}
	
	@Override
	public int hashCode() {		
		return Objects.hash(usuario, permissao);
	}	

}
