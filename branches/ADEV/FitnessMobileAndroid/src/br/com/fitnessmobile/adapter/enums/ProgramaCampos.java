package br.com.fitnessmobile.adapter.enums;

public enum ProgramaCampos {
	ID ("pro_id"),
	NOME ("pro_nome"),
	DATA_INICIAL ("pro_data_inicial"),
	DATA_FINAL ("pro_data_final");
	
	private String campo;
	
	ProgramaCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}
