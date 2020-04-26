package com.edwin.works.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edwin.works.domain.model.UsuarioPermissao;
import com.edwin.works.domain.model.UsuarioPermissaoPK;

@Repository
public interface UsuarioPermissaoRepository extends CrudRepository<UsuarioPermissao, UsuarioPermissaoPK>{

}
