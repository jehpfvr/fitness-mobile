package br.com.fitnessmobile.model;

import java.io.Serializable;

public class Musculo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int imagem;
	
	public Musculo() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getImagem() {
		return imagem;
	}

	public void setImagem(int imagem) {
		this.imagem = imagem;
	}

}
