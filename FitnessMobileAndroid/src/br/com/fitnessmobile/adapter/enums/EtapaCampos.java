package br.com.fitnessmobile.adapter.enums;

public enum EtapaCampos {
	ID ("eta_id"),
	NOME ("eta_nome"),
	DATA_INICIAL ("eta_data_inicial"),
	DATA_FINAL ("eta_data_final"),
	PROGRAMA_ID ("eta_pro_id");
	
	private String campo;
	
	EtapaCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
}
