package br.com.fitnessmobile.model;

import br.com.fitnessmobile.R;

public enum Musculo {
	
	// Declara��o das Constantes (Sintaxe: Id do Musculo no Banco, Nome do Musculo, int Icone)
	MUSCULO_UM (1, "Musculo Um", R.drawable.icon),
	MUSCULO_DOIS (2, "Musculo Dois", R.drawable.icon),
	MUSCULO_TRES (3, "Musculo Tres", R.drawable.icon);
	
	// Defini��o das Constantes
	private final int musculo_id;
	private final String musculo_nome;
	private final int musculo_icone;
	
	// M�todos para acessar os valores
	public int getMusculoId() {
		return this.musculo_id;
	}
	public String getMusculoNome() {
		return this.musculo_nome;
	}
	public int getMusculoIcone() {
		return this.musculo_icone;
	}
	
	// M�todos que define as constantes
	Musculo(int musculoId, String musculoNome, int musculoIcone) {
		this.musculo_id = musculoId;
		this.musculo_nome = musculoNome;
		this.musculo_icone = musculoIcone;
	}
}