package br.com.fitnessmobile.service;

import android.location.Location;

public class EstatisticaGPS {
	
	private long tempo;
	private long tempoPercorrido;
	private double distancia;
	private double velocidade;
	private double velocidadeMaxima;
	private Location ultimoLocation;
	private boolean marcaFim;
	private double calorias;

	public EstatisticaGPS() {
		this.velocidadeMaxima = 0;
		this.velocidade = 0;
	}
	
	public boolean isMarcaFim() {
		return marcaFim;
	}

	public void setMarcaFim(boolean marcaFim) {
		this.marcaFim = marcaFim;
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


	public Location getUltimoLocation() {
		return ultimoLocation;
	}


	public void setUltimoLocation(Location ultimoLocation) {
		this.ultimoLocation = ultimoLocation;
	}

	public double getVelocidadeMedia() {
		if(Double.isNaN(distancia/tempoPercorrido)) return Double.valueOf(0);
		return (distancia/tempoPercorrido) *3.6;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public long getTempoPercorrido() {
		return tempoPercorrido;
	}

	public void setTempoPercorrido(long tempoPercorrido) {
		this.tempoPercorrido += Math.abs(tempoPercorrido);
	}
	
	
	
	
	
	
 
	
}
