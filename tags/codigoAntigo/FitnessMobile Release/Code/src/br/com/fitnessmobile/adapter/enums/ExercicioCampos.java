package br.com.fitnessmobile.adapter.enums;

public enum ExercicioCampos {
	
	// Tabela Exercicios
	ID ("_id"),
	NOME ("nome"),
	DESCRICAO ("descricao"),
	SITUACAO ("situacao"),
	TIPO ("tipo"),
	INDICE_CALORICO ("indicecalorico"),
	MUSCULO ("musculo"),
	GRUPO_MUSCULAR ("grupomuscular");
	
	private String campo;
	
	ExercicioCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
}