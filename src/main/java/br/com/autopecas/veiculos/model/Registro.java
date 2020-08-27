package br.com.autopecas.veiculos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REGISTRO")
public class Registro implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private String descricaoRegistro;
	private Date dtInclusaoRegistro;
	private double precoRegistro;
	private String modeloVeiculo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricaoRegistro() {
		return descricaoRegistro;
	}

	public void setDescricaoRegistro(String descricaoRegistro) {
		this.descricaoRegistro = descricaoRegistro;
	}

	public Date getDtInclusaoRegistro() {
		return dtInclusaoRegistro;
	}

	public void setDtInclusaoRegistro(Date dtInclusaoRegistro) {
		this.dtInclusaoRegistro = dtInclusaoRegistro;
	}

	public double getPrecoRegistro() {
		return precoRegistro;
	}

	public void setPrecoRegistro(double precoRegistro) {
		this.precoRegistro = precoRegistro;
	}

	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
