package br.com.fitnessmobile.adapter.enums;

public enum AnaerobicoCampos {
	
	ID ("_id"),
	SERIE ("serie"),
	PESO ("peso"),
	REPETICAO("repeticao");
	
	private String campo;
	
	private AnaerobicoCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}
