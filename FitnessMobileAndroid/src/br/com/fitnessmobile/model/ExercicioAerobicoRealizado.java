package br.com.fitnessmobile.model;

import java.io.Serializable;

public class ExercicioAerobicoRealizado implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private long duracao;
	private Double distancia;
	private Double velocidade;
	private ExercicioRealizado exercicioRealizado;
	
	public ExercicioAerobicoRealizado() {
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

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}

	public ExercicioRealizado getExercicioRealizado() {
		return exercicioRealizado;
	}

	public void setExercicioRealizado(ExercicioRealizado exercicioRealizado) {
		this.exercicioRealizado = exercicioRealizado;
	}
	
}
