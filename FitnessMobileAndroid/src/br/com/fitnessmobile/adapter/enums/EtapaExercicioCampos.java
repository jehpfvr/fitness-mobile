package br.com.fitnessmobile.adapter.enums;

public enum EtapaExercicioCampos {
	ID ("_id"),
	FLAG ("flag"),
	ETAPA_ID ("etapaid"),
	EXERCICIO_ID ("exercicioid"),
	DIA_ID ("diaid"),
	DATA_BAIXA ("dtbaixa"),
	TIPO_ID ("tipoid");
	
	private String campo;
	
	EtapaExercicioCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
}
