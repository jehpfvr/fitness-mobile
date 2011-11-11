package br.com.fitnessmobile.model;

import java.io.Serializable;

public class Anaerobico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer repeticoes;
	private float peso;
	private Integer serie;
	private Integer idEta;
	
	public Integer getIdEta() {
		return idEta;
	}

	public void setIdEta(Integer idEta) {
		this.idEta = idEta;
	}

	public Anaerobico() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(Integer repeticoes) {
		this.repeticoes = repeticoes;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}	

}
