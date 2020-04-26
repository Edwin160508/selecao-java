package com.edwin.works.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edwin.works.domain.model.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long>{

}
