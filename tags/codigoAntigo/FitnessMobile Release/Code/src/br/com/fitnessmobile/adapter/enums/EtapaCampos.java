package br.com.fitnessmobile.adapter.enums;

public enum EtapaCampos {
	ID ("_id"),
	NOME ("nome"),
	PROGRAMA_ID ("programaid");
	
	private String campo;
	
	EtapaCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
}
