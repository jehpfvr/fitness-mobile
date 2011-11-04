package br.com.fitnessmobile.adapter.enums;

public enum AnaerobicoCampos {
	
	ID ("ana_id"),
	SERIE ("ana_serie"),
	PESO ("ana_peso"),
	REPETICAO("ana_repeticao"),
	IDEta ("ana_ete_id");
	
	private String campo;
	
	private AnaerobicoCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}
