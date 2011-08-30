package br.com.fitnessmobile.service;

import java.text.DecimalFormat;

import android.text.format.DateFormat;

public class EstatisticaGPS {
	
	private long tempoInicial;
	private long tempoFinal;
	private long tempoEmMovimento;
	private long tempoTotal;
	private long tempoEmAndamento;
	private double distancia;
	private double velocidade;
	private double velocidadeMaxima;
	private DecimalFormat df;

	
	
	public EstatisticaGPS() {
		this.df = new DecimalFormat("0.00");
	}



	public long getTempoInicial() {
		return tempoInicial;
	}



	public void setTempoInicial(long tempoInicial) {
		this.tempoInicial = tempoInicial;
	}



	public long getTempoFinal() {
		return tempoFinal;
	}



	public void setTempoFinal(long tempoFinal) {
		this.tempoFinal = tempoFinal;
	}



	public long getTempoEmMovimento() {
		return tempoEmMovimento;
	}



	public void setTempoEmMovimento(long tempoEmMovimento) {
		this.tempoEmMovimento += tempoEmMovimento;
	}



	public long getTempoTotal() {
		return tempoTotal;
	}



	public void setTempoTotal(long tempoTotal) {
		this.tempoTotal = tempoTotal;
	}



	public CharSequence getDistancia() {
		return df.format(distancia);
	}



	public void setDistancia(double distancia) {
		this.distancia += distancia;
	}



	public double getVelocidade() {
		return velocidade;
	}



	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}



	public double getVelocidadeMaxima() {
		return velocidadeMaxima;
	}
	
	public CharSequence getTempoEmAndamento() {
		return DateFormat.format("mm:ss", tempoEmAndamento);
	}



	public void setTempoEmAndamento(long tempoEmAndamento) {
		this.tempoEmAndamento = tempoEmAndamento;
	}

	public void setVelocidadeMaxima(double velocidadeMaxima) {
		this.velocidadeMaxima = velocidadeMaxima;
	}
	
	public CharSequence getVelocidadeMovimento(){
		return this.df.format((distancia/((double)tempoEmMovimento/1000))*3.6);
	}
	
	public CharSequence getVelocidadeMedia(){
		return this.df.format(distancia/((double)tempoTotal/1000));
	}
 
	
}
