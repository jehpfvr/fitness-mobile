package br.com.fitnessmobile.model;

import java.io.Serializable;

public class Configuracao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String metragem;
	private String kilometragem;
	private String Pesagem;
	
	public Configuracao() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetragem() {
		return metragem;
	}

	public void setMetragem(String metragem) {
		this.metragem = metragem;
	}

	public String getKilometragem() {
		return kilometragem;
	}

	public void setKilometragem(String kilometragem) {
		this.kilometragem = kilometragem;
	}

	public String getPesagem() {
		return Pesagem;
	}

	public void setPesagem(String pesagem) {
		Pesagem = pesagem;
	}
	
}
