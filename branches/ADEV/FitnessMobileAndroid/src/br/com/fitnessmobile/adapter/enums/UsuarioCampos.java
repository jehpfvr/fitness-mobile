package br.com.fitnessmobile.adapter.enums;

public enum UsuarioCampos {
	ID ("_id"),
	PESO ("peso"),
	ALTURA ("altura"),
	BICEPS_ESQUERDO ("biceps_esquerdo"), 
	BICEPS_DIREITO ("biceps_direito"),
	TRICEPS_ESQUERDO ("triceps_esquerdo"),
	TRICEPS_DIREITO ("triceps_direito"),
	CINTURA ("cintura"),
	PEITORAL ("peitoral"),
	COXA_ESQUERDA ("coxa_esquerda"),
	COXA_DIREITA ("coxa_direita"),
	PANTURRILHA_ESQUERDA ("panturrilha_esquerda"),
	PANTURRILHA_DIREITA ("panturrilha_direita"),
	QUADRIL ("quadril"),
	DATA ("data");
	
	private String campo;
	
	UsuarioCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}