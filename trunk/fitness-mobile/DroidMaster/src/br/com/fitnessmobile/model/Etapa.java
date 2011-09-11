package br.com.fitnessmobile.model;

import java.io.Serializable;
import java.util.List;

public class Etapa implements Serializable{
	
	private String nome;
	private List<Exercicio> exercicios;
	
	public Etapa() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}
	
}
