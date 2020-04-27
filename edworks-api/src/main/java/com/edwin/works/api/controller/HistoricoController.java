package com.edwin.works.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edwin.works.api.event.RecursoCriadoEvent;
import com.edwin.works.api.model.HistoricoIntputModel;
import com.edwin.works.api.model.HistoricoOutputModel;
import com.edwin.works.domain.model.Historico;
import com.edwin.works.domain.repository.HistoricoRepository;
import com.edwin.works.domain.service.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

	@Autowired
	private HistoricoService service;
	
	@Autowired
	private HistoricoRepository repository;	
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<List<Historico>> listar(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HistoricoOutputModel> buscarPorId(@PathVariable Long id){
		Optional<Historico> historicoEncontrado = service.verificaSeExiste(id);
		if(historicoEncontrado.isPresent()) {
			return ResponseEntity.ok(service.toModel(historicoEncontrado.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<HistoricoOutputModel> salvar(@Valid @RequestBody HistoricoIntputModel historicoInput, HttpServletResponse response){
		HistoricoOutputModel historicoSalvo = service.salvar(null,historicoInput);
		publisher.publishEvent(new RecursoCriadoEvent(historicoSalvo, response, historicoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(historicoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HistoricoOutputModel> atualizar(@PathVariable Long id, @Valid @RequestBody HistoricoIntputModel historicoInput){
		Optional<Historico> historicoEncontrado = service.verificaSeExiste(id);
		if(historicoEncontrado.isPresent()) {
			return ResponseEntity.ok(service.salvar(id, historicoInput));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
}
