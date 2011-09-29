package br.com.fitnessmobile.model;

import java.io.Serializable;

public class Medida implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private long dataCadastro;
	private Pessoa pessoa;
	private Musculo musculo;
	
	public Medida() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(long dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Musculo getMusculo() {
		return musculo;
	}

	public void setMusculo(Musculo musculo) {
		this.musculo = musculo;
	}	
	
}
