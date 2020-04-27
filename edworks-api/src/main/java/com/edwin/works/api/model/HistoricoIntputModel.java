package com.edwin.works.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edwin.works.domain.model.Empresa;
import com.edwin.works.domain.model.Produto;
import com.edwin.works.domain.model.RegiaoEstado;

public class HistoricoIntputModel {
	private Long id;
	@NotBlank
	private String municipio;
	@NotNull
	private LocalDate dataColeta;
	@NotNull	
	private BigDecimal valorVendar;
	@NotNull
	private BigDecimal valorCompra;
	@NotBlank	
	private String unidadeMedida;
	@NotNull	
	private Produto produto;
	@NotNull	
	private RegiaoEstado regiaoEstado;
	@NotNull
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public LocalDate getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(LocalDate dataColeta) {
		this.dataColeta = dataColeta;
	}

	public BigDecimal getValorVendar() {
		return valorVendar;
	}

	public void setValorVendar(BigDecimal valorVendar) {
		this.valorVendar = valorVendar;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public RegiaoEstado getRegiaoEstado() {
		return regiaoEstado;
	}

	public void setRegiaoEstado(RegiaoEstado regiaoEstado) {
		this.regiaoEstado = regiaoEstado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
