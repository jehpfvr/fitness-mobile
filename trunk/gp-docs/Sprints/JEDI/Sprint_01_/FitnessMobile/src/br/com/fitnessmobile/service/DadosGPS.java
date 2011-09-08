package br.com.fitnessmobile.service;

public class DadosGPS {
	
	private CharSequence distancia;
	private CharSequence duracao;
	private CharSequence velocidade;
	private CharSequence aceleracao;
	
	
	public DadosGPS() {
	}


	public CharSequence getDistancia() {
		return distancia;
	}


	public CharSequence getVelocidade() {
		return velocidade;
	}


	public CharSequence getDuracao() {
		return duracao;
	}
	

	public CharSequence getAceleracao() {
		return aceleracao;
	}

	public void setDuracao(CharSequence duracao) {
		this.duracao = duracao;
	}


	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}


	public void setVelocidade(CharSequence velocidade) {
		this.velocidade = velocidade;
	}

	public void setAceleracao(CharSequence aceleracao) {
		this.aceleracao = aceleracao;
	}


	public void setDistancia(CharSequence distancia) {
		this.distancia = distancia;
	}
	
	
 
	
}
