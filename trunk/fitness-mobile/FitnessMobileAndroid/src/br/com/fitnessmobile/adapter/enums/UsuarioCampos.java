package br.com.fitnessmobile.adapter.enums;

public enum UsuarioCampos {
	ID ("med_id"),
	PESO ("med_peso"),
	BICEPS_ESQUERDO ("med_biceps_esquerdo"), 
	BICEPS_DIREITO ("med_biceps_direito"),
	TRICEPS_ESQUERDO ("med_triceps_esquerdo"),
	TRICEPS_DIREITO ("med_triceps_direito"),
	CINTURA ("med_cintura"),
	PEITORAL ("med_peitoral"),
	COXA_ESQUERDA ("med_coxa_esquerda"),
	COXA_DIREITA ("med_coxa_direita"),
	PANTURRILHA_ESQUERDA ("med_panturrilha_esquerda"),
	PANTURRILHA_DIREITA ("med_panturrilha_direita"),
	QUADRIL ("med_quadril"),
	DATA ("med_data_cadastro"),
	PESSOA_ID("med_pes_id");
	
	private String campo;
	
	UsuarioCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}