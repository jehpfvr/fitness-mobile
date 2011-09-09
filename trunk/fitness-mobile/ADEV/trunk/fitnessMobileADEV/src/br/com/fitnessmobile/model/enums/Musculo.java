package br.com.fitnessmobile.model.enums;

import br.com.fitnessmobile.R;

public enum Musculo {
	
	// Declaração das Constantes (Sintaxe: Id do Musculo no Banco, Nome do Musculo, int Icone)
	MUSCULO_UM (1, "Musculo Um", R.drawable.icon),
	MUSCULO_DOIS (2, "Musculo Dois", R.drawable.icon),
	MUSCULO_TRES (3, "Musculo Tres", R.drawable.icon);
	
	// Definição das Constantes
	private final int musculo_id;
	private final String musculo_nome;
	private final int musculo_icone;
	
	// Métodos para acessar os valores
	public int getMusculoId() {
		return this.musculo_id;
	}
	public String getMusculoNome() {
		return this.musculo_nome;
	}
	public int getMusculoIcone() {
		return this.musculo_icone;
	}
	
	// Métodos que define as constantes
	Musculo(int musculoId, String musculoNome, int musculoIcone) {
		this.musculo_id = musculoId;
		this.musculo_nome = musculoNome;
		this.musculo_icone = musculoIcone;
	}
}