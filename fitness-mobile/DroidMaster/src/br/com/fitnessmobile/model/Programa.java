package br.com.fitnessmobile.model;

import java.io.Serializable;
import java.util.List;

public class Programa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private long dataInicio;
	private long dataFim;
	private List<Etapa> etapas;

	
	public Programa() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(long dataInicio) {
		this.dataInicio = dataInicio;
	}

	public long getDataFim() {
		return dataFim;
	}

	public void setDataFim(long dataFim) {
		this.dataFim = dataFim;
	}	
}
