package br.com.fitnessmobile.adapter.enums;

public enum EtapaCampos {
	ID ("_id"),
	NOME ("nome"),
	DATA_INICIAL ("data_inicial"),
	DATA_FINAL ("data_final"),
	PROGRAMA_ID ("programaid");
	
	private String campo;
	
	EtapaCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
}
