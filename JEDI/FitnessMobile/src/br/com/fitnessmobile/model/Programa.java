package br.com.fitnessmobile.model;

import java.util.List;

public class Programa {
	
	private String nome;
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
	
}
