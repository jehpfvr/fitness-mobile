package br.com.fitnessmobile.service;

public class EstatisticaGPS {
	
	private long tempo;
	private double distancia;
	private double velocidade;
	private double velocidadeMaxima;
	private double testeVelocidade;

	public EstatisticaGPS() {
		this.velocidadeMaxima = 0;
	}
	
	
	public double getTesteVelocidade() {
		return testeVelocidade;
	}


	public void setTesteVelocidade(double testeVelocidade) {
		this.testeVelocidade = testeVelocidade;
	}


	public double getDistancia() {
		return distancia/1000;
	}
	
	public void setDistancia(double distancia) {
		this.distancia += distancia;
	}
	
	public double getVelocidade() {
		return velocidade;
	}
	
	public void setVelocidade(double velocidade) {
		if(velocidadeMaxima<velocidade) velocidadeMaxima = velocidade;
		this.velocidade = velocidade;
	}

	public double getVelocidadeMaxima() {
		return velocidadeMaxima;
	}
	
	public long getTempoEmAndamento() {
		return tempo;
	}

	public void setTempoEmAndamento(long tempoEmAndamento) {
		this.tempo = tempoEmAndamento;
	}
	
 
	
}
