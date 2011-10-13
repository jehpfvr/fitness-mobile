package br.com.fitnessbd;

public enum ProgramaCampos {
	ID ("_id"),
	NOME ("nome"),
	DATA_INICIAL ("data_inicial"),
	DATA_FINAL ("data_final");
	
	private String campo;
	
	ProgramaCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}
