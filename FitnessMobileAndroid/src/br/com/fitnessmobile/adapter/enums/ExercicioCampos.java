package br.com.fitnessmobile.adapter.enums;

public enum ExercicioCampos {
	
	// Tabela Exercicios
	ID ("exe_id"),
	NOME ("exe_nome"),
	DESCRICAO ("exe_descricao"),
	SITUACAO ("exe_situacao"),
	TIPO ("exe_tipo"),
	INDICE_CALORICO ("exe_indice_calorico"),
	
	MUSCULO ("exe_musculo"),
	GRUPO_MUSCULAR ("exe_grupomuscular");
	
	private String campo;
	
	ExercicioCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
}