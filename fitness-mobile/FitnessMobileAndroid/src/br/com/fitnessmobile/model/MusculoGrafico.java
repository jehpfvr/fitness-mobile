package br.com.fitnessmobile.model;

public class MusculoGrafico {

	private String nome;
	private int positionX;
	private int positionY;
	private float tamanho;
	
	public MusculoGrafico() { }
	
	public MusculoGrafico(String nome, int positionX, int positionY, Float tamanho) {
		this.nome 		= nome;
		this.positionX 	= positionX;
		this.positionY 	= positionY;
		this.tamanho 	= tamanho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public Float getTamanho() {
		return tamanho;
	}

	public void setTamanho(Float tamanho) {
		this.tamanho = tamanho;
	}
	
}
