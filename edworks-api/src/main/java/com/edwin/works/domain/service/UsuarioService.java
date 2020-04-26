package com.edwin.works.domain.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edwin.works.domain.exception.NegocioException;
import com.edwin.works.domain.model.Usuario;
import com.edwin.works.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository; 
	
	public Usuario salvar(Usuario usuario) {
		validaUsuarioEmailExistente(usuario);
		//criar um UsuarioVO com aributo perfil para set das permissões
		//verificar perfil ADMIN ou PESQUISADOR para insert na tabela usuario_permissao
		//ADMIN insere todas as permissões
		//PESQUISADOR insere permissões refenrete apenas a pesquisa
		return repository.save(usuario);
	}
	
	public void validaUsuarioEmailExistente(Usuario usuario) {
		Usuario usuarioEncontrado = repository.findByEmail(usuario.getEmail());
		if(usuarioEncontrado != null && !usuarioEncontrado.equals(usuario)) {
			throw new NegocioException("Já existe usuário cadastrado com este e-mail.");
		}
	}
	
	public void remover(Long id) {
		Usuario usuarioEncontrado = verificaSeExiste(id);
		repository.delete(usuarioEncontrado);
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {		
		Usuario usuarioEncontrado = buscarUsuarioPeloId(id);
		BeanUtils.copyProperties(usuario, usuarioEncontrado);		
		return repository.save(usuarioEncontrado);	
	}
	
	public Usuario buscarUsuarioPeloId(Long id) {
		return repository.findById(id).get();
	}
	
	public Usuario verificaSeExiste(Long id) {
		Usuario usuarioEncontrado = buscarUsuarioPeloId(id);
		if(Objects.isNull(usuarioEncontrado)) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioEncontrado;
	}
	
}
