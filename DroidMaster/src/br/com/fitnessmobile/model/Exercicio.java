package br.com.fitnessmobile.model;

import java.io.Serializable;
import java.util.List;

public class Exercicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private Musculo musculoPrincipal;
	private char tipo;
	private Double indiceCalorico;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public Double getIndiceCalorico() {
		return indiceCalorico;
	}

	public void setIndiceCalorico(Double indiceCalorico) {
		this.indiceCalorico = indiceCalorico;
	}
	
	
	
	
	

}
