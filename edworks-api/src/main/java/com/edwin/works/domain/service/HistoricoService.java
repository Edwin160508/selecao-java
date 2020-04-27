package com.edwin.works.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edwin.works.api.model.HistoricoIntputModel;
import com.edwin.works.api.model.HistoricoOutputModel;
import com.edwin.works.domain.model.Historico;
import com.edwin.works.domain.repository.HistoricoRepository;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void remover(Long id) {
		Optional<Historico> historicoEncontrado = verificaSeExiste(id);
		repository.delete(historicoEncontrado.get());
	}
	public HistoricoOutputModel salvar(Long id, HistoricoIntputModel historicoInput) {
		HistoricoOutputModel retorno = null;
		if(Objects.isNull(id)) {
			Historico historicoSalvo = repository.save(toEntity(historicoInput));
			retorno = toModel(historicoSalvo);
		}else {
			historicoInput.setId(id);
			Historico historicoAtualizado = repository.save(toEntity(historicoInput));
			retorno = toModel(historicoAtualizado);
		}
		
		return retorno;		
	}
	
	
	public Optional<Historico> verificaSeExiste(Long id) {
		Optional<Historico> historicoEncontrado = buscarUsuarioPeloId(id);
		if(!historicoEncontrado.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return historicoEncontrado;
	}
	
	private Optional<Historico> buscarUsuarioPeloId(Long id){
		return repository.findById(id);
	}
	
	public HistoricoOutputModel toModel(Historico entity) {
		return modelMapper.map(entity, HistoricoOutputModel.class);
	}
	
	private Historico toEntity(HistoricoIntputModel historicoInput) {
		return modelMapper.map(historicoInput, Historico.class);
	}
	
	public List<HistoricoOutputModel> toCollectionModel(List<Historico> entityList){
		return entityList.stream()
				.map(historico -> toModel(historico))
				.collect(Collectors.toList());
	}
}
