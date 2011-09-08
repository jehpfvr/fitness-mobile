package br.com.fitnessmobile.model;

public class ExercicioAerobico extends Exercicio {
	private double periodo;
	private double distancia;
	
	public ExercicioAerobico() {
	}

	public double getPeriodo() {
		return periodo;
	}

	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
}
