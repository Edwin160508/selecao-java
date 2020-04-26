package com.edwin.works.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edwin.works.api.model.UsuarioInputModel;
import com.edwin.works.api.model.UsuarioOutputModel;
import com.edwin.works.domain.exception.NegocioException;
import com.edwin.works.domain.model.Permissao;
import com.edwin.works.domain.model.Usuario;
import com.edwin.works.domain.model.UsuarioPermissao;
import com.edwin.works.domain.repository.PermissaoRepository;
import com.edwin.works.domain.repository.UsuarioPermissaoRepository;
import com.edwin.works.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioPermissaoRepository usuarioPermissaoRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public UsuarioOutputModel salvar(Long id, UsuarioInputModel usuarioInput) {
		UsuarioOutputModel retorno = null;
		if(Objects.isNull(id)) {
			if(usuarioInput.getPerfil().equalsIgnoreCase("ADMIN")) {			
				retorno = salvaUsuarioPermissoesAdmin(usuarioInput);
			}else if(usuarioInput.getPerfil().equalsIgnoreCase("PESQUISADOR")) {
				retorno = salvaUsuarioPermissoesPesquisa(usuarioInput);
			}else {
				throw new NegocioException("Perfil de usuário inválido.");
			}
		}else {
			if(usuarioInput.getPerfil().equalsIgnoreCase("ADMIN")) {			
				retorno = atualizaUsuarioPermissoesAdmin(id, usuarioInput);
			}else if(usuarioInput.getPerfil().equalsIgnoreCase("PESQUISADOR")) {
				retorno = atualizaUsuarioPermissoesPesquisa(id,usuarioInput);
			}else {
				throw new NegocioException("Perfil de usuário inválido.");
			}
		}
		return retorno;
	}
	
	private UsuarioOutputModel salvaUsuarioPermissoesAdmin(UsuarioInputModel usuarioInput) {
		Usuario entity = toEntity(usuarioInput);
		validaUsuarioEmailExistente(entity);	
		Usuario usuarioSalvo = repository.save(entity);				
		List<UsuarioPermissao> permissoesDoUsuario = permissoesAdmin(usuarioSalvo);
		for(UsuarioPermissao p: permissoesDoUsuario) {
			usuarioPermissaoRepository.save(p);
		}								
		return toModel(usuarioSalvo);
	}
	
	private UsuarioOutputModel atualizaUsuarioPermissoesAdmin(Long id, UsuarioInputModel usuarioInput) {		
		verificaSeExiste(id);
		Usuario entity = toEntity(usuarioInput);
		entity.setId(id);
		Usuario usuarioSalvo = repository.save(entity);				
		List<UsuarioPermissao> permissoesDoUsuario = permissoesAdmin(usuarioSalvo);
		for(UsuarioPermissao p: permissoesDoUsuario) {
			usuarioPermissaoRepository.save(p);
		}								
		return toModel(usuarioSalvo);
	}
	
	private UsuarioOutputModel salvaUsuarioPermissoesPesquisa(UsuarioInputModel usuarioInput) {
		Usuario entity = toEntity(usuarioInput);
		validaUsuarioEmailExistente(entity);	
		Usuario usuarioSalvo = repository.save(entity);				
		List<UsuarioPermissao> permissoesDoUsuario = permissoesPesquisa(usuarioSalvo);
		for(UsuarioPermissao p: permissoesDoUsuario) {
			usuarioPermissaoRepository.save(p);
		}								
		return toModel(usuarioSalvo);
	}
	
	private UsuarioOutputModel atualizaUsuarioPermissoesPesquisa(Long id, UsuarioInputModel usuarioInput) {
		verificaSeExiste(id);
		Usuario entity = toEntity(usuarioInput);
		entity.setId(id);
		Usuario usuarioSalvo = repository.save(entity);				
		List<UsuarioPermissao> permissoesDoUsuario = permissoesPesquisa(usuarioSalvo);
		for(UsuarioPermissao p: permissoesDoUsuario) {
			usuarioPermissaoRepository.save(p);
		}								
		return toModel(usuarioSalvo);
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
	
	public List<UsuarioOutputModel> listar(){
		return toCollectionModel(repository.findAll());
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
	
	private UsuarioOutputModel toModel(Usuario entity) {
		return modelMapper.map(entity, UsuarioOutputModel.class);
	}
	private Usuario toEntity(UsuarioInputModel usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	private List<UsuarioOutputModel> toCollectionModel(List<Usuario> entityList){
		return entityList.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}
	
	private List<UsuarioPermissao> permissoesAdmin(Usuario usuario) {
		List<UsuarioPermissao> permissoesMapeadas = new ArrayList<>();
		List<Permissao> listaPermissoes = carregaListaComTodasPermissoes();
		for(int i=0; i<listaPermissoes.size(); i++) {
			UsuarioPermissao userPermissao = new UsuarioPermissao();
			userPermissao.setUsuario(usuario);
			userPermissao.setPermissao(listaPermissoes.get(i));
			permissoesMapeadas.add(userPermissao);
		}
		return permissoesMapeadas;
		
	}
	private List<Permissao> carregaListaComTodasPermissoes(){
		return permissaoRepository.findAll();
	}
	
	private List<Permissao> carregaListaPermissaoPesquisa(){
		return permissaoRepository.findByDescricaoContaining("PESQUISAR");
	}
	
	private List<UsuarioPermissao> permissoesPesquisa(Usuario usuario) {
		List<UsuarioPermissao> permissoesMapeadas = new ArrayList<>();
		List<Permissao> listaPermissoes = carregaListaPermissaoPesquisa();
		for(int i=0; i<listaPermissoes.size(); i++) {
			UsuarioPermissao userPermissao = new UsuarioPermissao();
			userPermissao.setUsuario(usuario);
			userPermissao.setPermissao(listaPermissoes.get(i));
			permissoesMapeadas.add(userPermissao);
		}
		return permissoesMapeadas;
	}
}
