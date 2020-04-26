package com.edwin.works.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edwin.works.domain.model.RegiaoEstado;

@Repository
public interface RegiaoEstadoEstadoRepository extends JpaRepository<RegiaoEstado, Long>{

}
