package br.com.fitnessmobile.model;

import java.io.Serializable;

public class Aerobico implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private long duracao;
	private Double distancia;
	private Double velocidade;
	private Integer id_exc;

	public Aerobico() {
	}

	public Integer getId_exc() {
		return id_exc;
	}

	public void setId_exc(Integer id_exc) {
		this.id_exc = id_exc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}

	public Double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
}