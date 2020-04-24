package com.edwin.works.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String municipio;
	
	@NotNull
	@Column(name = "data_coleta")
	private LocalDate dataColeta;
	
	@Column(name = "valor_venda")
	private BigDecimal valorVendar;
	
	@NotNull
	@Column(name = "valor_compra")
	private BigDecimal valorCompra;
	
	@NotBlank
	@Column(name = "unidade_medida")
	private String unidadeMedida;
	
	@NotNull
	@ManyToOne	
	private Produto produto;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "regiao_estado_id")
	private RegiaoEstado regiaoEstado;
	
	@NotNull
	@ManyToOne
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
