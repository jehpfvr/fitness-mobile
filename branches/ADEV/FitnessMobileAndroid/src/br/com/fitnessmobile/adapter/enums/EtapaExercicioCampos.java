package br.com.fitnessmobile.adapter.enums;

public enum EtapaExercicioCampos {
	ID ("ete_id"),
	FLAG ("ete_flag"),
	ETAPA_ID ("ete_eta_id"),
	EXERCICIO_ID ("ete_exe_id"),
	DIA_ID ("ete_diaid"),
	DATA_BAIXA ("ete_dtbaixa"),
	TIPO_ID ("ete_tipo_id");
	
	private String campo;
	
	EtapaExercicioCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
}
