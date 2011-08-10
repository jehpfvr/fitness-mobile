package br.com.fitnessmobile.model;

import java.util.List;

public class Exercicio {
	
	private String nome;
	private String descricao;
	private Musculo musculoPrincipal;
	private List<Musculo> grupoMuscular;
	
	public Exercicio() {
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Musculo getMusculoPrincipal() {
		return musculoPrincipal;
	}
	public void setMusculoPrincipal(Musculo musculoPrincipal) {
		this.musculoPrincipal = musculoPrincipal;
	}
	public List<Musculo> getGrupoMuscular() {
		return grupoMuscular;
	}
	public void setGrupoMuscular(List<Musculo> grupoMuscular) {
		this.grupoMuscular = grupoMuscular;
	}
	
	
	

}
