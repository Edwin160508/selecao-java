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
import com.edwin.works.api.model.UsuarioInputModel;
import com.edwin.works.api.model.UsuarioOutputModel;
import com.edwin.works.domain.model.Usuario;
import com.edwin.works.domain.repository.UsuarioRepository;
import com.edwin.works.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<UsuarioOutputModel>> listar(){
		return ResponseEntity.ok(service.toCollectionModel(repository.findAll()));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioOutputModel> salvar(@Valid @RequestBody UsuarioInputModel usuarioInput,  HttpServletResponse response){	
		UsuarioOutputModel usuarioSalvo = service.salvar(null,usuarioInput);
		publisher.publishEvent(new RecursoCriadoEvent(usuarioSalvo, response, usuarioSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioOutputModel> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioInputModel usuarioInput){
		Optional<Usuario> usuarioEncontrado = repository.findById(id);
		if(usuarioEncontrado.isPresent()) {
			return ResponseEntity.ok(service.salvar(id, usuarioInput));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioOutputModel> buscarPeloCodigo(@PathVariable Long id){
		Optional<Usuario> usuarioEncontrado = repository.findById(id);
		if(usuarioEncontrado.isPresent()) {
			return ResponseEntity.ok(service.toModel(usuarioEncontrado.get()));
		}
		return ResponseEntity.notFound().build();
		
	}
		
}
